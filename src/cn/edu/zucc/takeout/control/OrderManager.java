package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.IOrderManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanOrderdetail;
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.DBUtil;

public class OrderManager implements IOrderManager {
	
	@Override
	public List<BeanProductorder> loadOrders(BeanCustomer currentLoginUser) throws BaseException{
		List<BeanProductorder> result=new ArrayList<BeanProductorder>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select order_id,pre_id,add_id,shop_id,coup_id,rider_id,originprice,finalprice,starttime,requiretime,orderstate from productorder where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
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
					// TODO Auto-generated catch block
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
	
}
