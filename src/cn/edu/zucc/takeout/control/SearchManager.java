package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.ISearchManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanRiderbill;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.DBUtil;

public class SearchManager implements ISearchManager{
	
	@Override
	public List<BeanCustomer> loadCust(int custid) throws BaseException{
		List<BeanCustomer> result=new ArrayList<BeanCustomer>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select cust_id,cust_name,cust_gender,cust_password,cust_phone,cust_mail,cust_city,rig_time,ifVIP,VIPdeadline from customer where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, custid);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCustomer p=new BeanCustomer();
				p.setCust_id(rs.getInt(1));
				p.setCust_name(rs.getString(2));
				p.setCust_gender(rs.getString(3));
				p.setCust_password(rs.getString(4));
				p.setCust_phone(rs.getString(5));
				p.setCust_mail(rs.getString(6));
				p.setCust_city(rs.getString(7));
				p.setRig_time(rs.getTimestamp(8));
				p.setIfVIP(rs.getString(9));
				p.setvIPdeadline(rs.getTimestamp(10));
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
	public List<BeanRiderbill> loadRiderbill(int riderid) throws BaseException{
		List<BeanRiderbill> result=new ArrayList<BeanRiderbill>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select order_id,taketime,evaluate,income from riderbill where rider_id=? order by order_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, riderid);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanRiderbill s=new BeanRiderbill();
				s.setOrder_id(rs.getInt(1));
				s.setTaketime(rs.getTimestamp(2));
				s.setEvaluate(rs.getString(3));
				s.setIncome(rs.getFloat(4));
				result.add(s);
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
	public List<BeanProduct> loadPro(int proid) throws BaseException{
		List<BeanProduct> result=new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pro_id,shop_id,cate_id,pro_name,pro_price,pro_discount from product where pro_id=? order by cate_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, proid);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanProduct s=new BeanProduct();
				s.setPro_id(rs.getInt(1));
				s.setShop_id(rs.getInt(2));
				s.setCate_id(rs.getInt(3));
				s.setPro_name(rs.getString(4));
				s.setPro_price(rs.getFloat(5));
				s.setPro_discount(rs.getFloat(6));
				result.add(s);
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
