package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import cn.edu.zucc.takeout.itf.IOrderManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanOrderdetail;
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class OrderManager implements IOrderManager {
	
	@Override
	public List<BeanProductorder> loadOrders(BeanCustomer customer) throws BaseException{
		List<BeanProductorder> result=new ArrayList<BeanProductorder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select order_id,pre_id,add_id,shop_id,coup_id,rider_id,originprice,finalprice,starttime,requiretime,orderstate from productorder where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,customer.getCust_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanProductorder p=new BeanProductorder();
				p.setOrder_id(rs.getInt(1));
				p.setPre_id(rs.getInt(2));
				p.setAdd_id(rs.getInt(3));
				p.setShop_id(rs.getInt(4));
				p.setCoup_id(rs.getInt(5));
				p.setRider_id(rs.getInt(6));
				p.setOriginprice(rs.getFloat(7));
				p.setFinalprice(rs.getFloat(8));
				p.setStarttime(rs.getDate(9));
				p.setRequiretime(rs.getDate(10));
				p.setOrderstate(rs.getString(11));
				result.add(p);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return result;
	}
	
	@Override
	public List<BeanOrderdetail> loadDetails(BeanProductorder productorder) throws BaseException{
		List<BeanOrderdetail> result=new ArrayList<BeanOrderdetail>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pro_id,mount,price,perdiscount from orderdetail where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, productorder.getOrder_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanOrderdetail p=new BeanOrderdetail();
				p.setPro_id(rs.getInt(1));
				p.setMount(rs.getInt(2));
				p.setPrice(rs.getFloat(3));
				p.setPerdiscount(rs.getFloat(4));
				result.add(p);
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return result;
	}
	
	@Override
	public void evaluateRider(BeanProductorder order,String evaluate) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select evaluate from riderbill where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, order.getOrder_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				if(!rs.getString(1).equals("未评价")) {
					throw new BusinessException("您已评价过该骑手，评价为"+rs.getString(1)+"!");
				}
			}
			sql="select orderstate from productorder where order_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, order.getOrder_id());
			pst.execute();
			rs=pst.executeQuery();
			while(rs.next()) {
				if(rs.getString(1).equals("已送达")) {
					sql="update riderbill set evaluate=? where order_id=?";
					pst=conn.prepareStatement(sql);
					pst.setString(1, evaluate);
					pst.setInt(2, order.getOrder_id());
					pst.executeUpdate();
				}else {
					throw new BusinessException("该订单未送达或已取消，不能评价！");
				}
			}
			rs.close();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public void evaluateProduct(BeanOrderdetail product,String content,float start) throws BaseException{
		Connection conn=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = df.format(System.currentTimeMillis());
		Timestamp evaDate = Timestamp.valueOf(time);
		int shopid=0;
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_id from product where pro_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, product.getPro_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				shopid=rs.getInt(1);
			}
			sql="select eval_date,star,content from productevaluate where shop_id=? and pro_id=? and cust_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, shopid);
			pst.setInt(2, product.getPro_id());
			pst.setInt(3, BeanCustomer.currentLoginUser.getCust_id());
			pst.execute();
			rs=pst.executeQuery();
			if(rs.next()) {
				JOptionPane.showMessageDialog(null, "你已在 "+rs.getDate(1)+" 评价过该商品，评价星级为 "+rs.getFloat(2)+" 星，评价内容为 "+rs.getString(3), "错误",JOptionPane.ERROR_MESSAGE);
			}else {
				sql="insert into productevaluate(shop_id,pro_id,cust_id,content,eval_date,star,photo) values(?,?,?,?,?,?,?)";
				pst=conn.prepareStatement(sql);
				pst.setInt(1,shopid);
				pst.setInt(2, product.getPro_id());
				pst.setInt(3, BeanCustomer.currentLoginUser.getCust_id());
				pst.setString(4, content);
				pst.setTimestamp(5, evaDate);
				pst.setFloat(6, start);
				pst.setString(7, "没拍");
				pst.executeUpdate();
				rs.close();
				pst.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
	@Override
	public void deleteorders(BeanProductorder order) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from orderdetail where order_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, order.getOrder_id());
			pst.execute();
			sql="delete from productorder where order_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, order.getOrder_id());
			pst.execute();
			pst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			if(conn!=null)
				try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
}
