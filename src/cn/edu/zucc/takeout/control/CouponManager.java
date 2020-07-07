package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.ICouponManager;
import cn.edu.zucc.takeout.model.BeanCoupon;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class CouponManager implements ICouponManager{
	
	@Override
	public void addcou(BeanShopkeeper shop,String coupid,Float amount,int count,String start,String end) throws BaseException{
        Connection conn=null;
        if(coupid.equals("")||amount==null) {
        	throw new BusinessException("请将信息填写完整！");
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
            	throw new BusinessException("该优惠券已存在");
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
			if(rs.next()) throw new BusinessException("该优惠券属于某个用户并且暂未使用，不能删除!");
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
}
