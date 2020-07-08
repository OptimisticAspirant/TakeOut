package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.ICouponManager;
import cn.edu.zucc.takeout.model.BeanCoupon;
import cn.edu.zucc.takeout.model.BeanCouponhold;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanDiscount;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class CouponManager implements ICouponManager{
	
	@Override
	public void addcou(BeanShopkeeper shop,String coupid,Float amount,int count,String start,String end) throws BaseException{
        Connection conn=null;
        if(coupid.equals("")||amount==null) {
        	throw new BusinessException("�뽫��Ϣ��д������");
        }
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		java.sql.Date starttime = null;
		java.sql.Date endtime = null;
		try {
			starttime=new java.sql.Date((df.parse(start).getTime()));
			endtime = new java.sql.Date(df.parse(end).getTime());
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        try {
            conn=DBUtil.getConnection();
            String sql="select * from coupon where coup_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,coupid);
            ResultSet rs=pst.executeQuery();
            if(rs.next()) {
            	throw new BusinessException("���Ż�ȯ�Ѵ���");
            }
            sql="insert into coupon(coup_id,shop_id,coup_amount,coup_count,startdate,enddate) values(?,?,?,?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setString(1,coupid);
            pst.setString(2,shop.getShop_id());
            pst.setFloat(3,amount);
            pst.setInt(4, count);
            pst.setDate(5, starttime);
            pst.setDate(6, endtime);
            pst.execute();
			pst.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null) {
                try {
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
            }
        }
	}
	
	@Override
	public void deletecoup(BeanCoupon coup) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from couponhold where coup_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, coup.getCoup_id());
			pst.execute();
			ResultSet rs=pst.getResultSet();
			if(rs.next()) throw new BusinessException("���Ż�ȯ����ĳ���û�������δʹ�ã�����ɾ��!");
			rs.close();
			pst.close();
			sql="delete from coup where coup_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, coup.getCoup_id());
			pst.executeUpdate();
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
		
	}
	
	@Override
	public List<BeanCoupon> loadShopCoupon(BeanShopkeeper shop) throws BaseException{
		List<BeanCoupon> result=new ArrayList<BeanCoupon>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select coup_id,coup_amount,coup_count,startdate,enddate from coupon where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,shop.getShop_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCoupon p=new BeanCoupon();
				p.setCoup_id(rs.getString(1));
				p.setCoup_amount(rs.getFloat(2));
				p.setCoup_count(rs.getInt(3));
				p.setStartdate(rs.getDate(4));
				p.setEnddate(rs.getDate(5));
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
	public List<BeanCouponhold> loadCouponhold(BeanCustomer customer) throws BaseException{
		List<BeanCouponhold> result=new ArrayList<BeanCouponhold>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select coup_id,shop_id,hold_mount,subtract,hold_deadline from couponhold where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,customer.getCust_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCouponhold p=new BeanCouponhold();
				p.setCoup_id(rs.getString(1));
				p.setShop_id(rs.getString(2));
				p.setHold_mount(rs.getInt(3));
				p.setSubtract(rs.getFloat(4));
				p.setHold_deadline(rs.getDate(5));
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
	public List<BeanDiscount> loadCollect(BeanCustomer customer) throws BaseException{
		List<BeanDiscount> result=new ArrayList<BeanDiscount>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_id,coup_id,collect_count,collect_require from discount where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,customer.getCust_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanDiscount p=new BeanDiscount();
				p.setShop_id(rs.getString(1));
				p.setCoup_id(rs.getString(2));
				p.setCollect_count(rs.getInt(3));
				p.setCollect_require(rs.getInt(4));
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
	
	public void changecoupon(BeanDiscount collect) throws BaseException{
		Connection conn=null;
		int count=0;
		int require=0;
		float subtract=0;
		Date deadline=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select collect_count,collect_require from couponhold where cust_id=? and coup_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,collect.getCust_id());
			pst.setString(2,collect.getCoup_id());
			pst.executeUpdate();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
				require=rs.getInt(2);
			}
			if(count<require) {
				throw new BusinessException("�Ѷ�����������һ��Ż�ȯ��Ҫ������");
			}
			rs.close();
			pst.close();
			sql="update couponhold set collect_count=collect_count-? where cust_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1,require);
			pst.setString(2,collect.getCust_id());
			pst.executeUpdate();
			pst.close();
			sql="select coup_count,enddate from coupon where coup_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1,collect.getCoup_id());
			pst.executeUpdate();
			rs=pst.executeQuery();
			while(rs.next()) {
				subtract=rs.getFloat(1);
				deadline=rs.getDate(2);
			}
			rs.close();
			pst.close();
			sql="insert into couponhold(coup_id,cust_id,shop_id,hold_mount,subtract,hold_deadline) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql);
			pst.setString(1,collect.getCoup_id());
			pst.setString(2,collect.getCust_id());
			pst.setString(3, collect.getShop_id());
			pst.setInt(4, 1);
			pst.setFloat(5, subtract);
			pst.setDate(6, deadline);
			pst.executeUpdate();
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
	}
	
}
