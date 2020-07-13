package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeout.itf.ICartManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanPreferential;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class CartManager implements ICartManager{

	public static int keyID=0;
	
	@Override
	public int settle(BeanShopkeeper shop,BeanCustomer cust,int coupid, int addressid,float originprice,float finalprice,Date requiretime) throws BaseException{
		Connection conn=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = df.format(System.currentTimeMillis());
		Timestamp createTime = Timestamp.valueOf(time);
        try {
            conn=DBUtil.getConnection();
            String sql="insert into productorder(shop_id,cust_id,coup_id,add_id,originprice,finalprice,starttime,requiretime,orderstate) values(?,?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql,java.sql.PreparedStatement.RETURN_GENERATED_KEYS);
            pst.setInt(1,shop.getShop_id());
            pst.setInt(2,cust.getCust_id());
            pst.setInt(3,coupid);
            pst.setInt(4,addressid);
            pst.setFloat(5,originprice);
            pst.setFloat(6,finalprice);
            pst.setTimestamp(7,createTime);
            pst.setTimestamp(8,new java.sql.Timestamp(requiretime.getTime()));
            pst.setString(9,"等待骑手接单");
            pst.executeUpdate();
            ResultSet rs=pst.getGeneratedKeys();
            while(rs.next()) {
            	keyID=rs.getInt(1);
            }
			pst.close();
			sql="update riderbill set evaluate=? where order_id=?";
            pst=conn.prepareStatement(sql);
            pst.setString(1,"未评价");
            pst.setInt(2,keyID);
            pst.executeUpdate();
            sql="update discount set collect_count=collect_count+1 where shop_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
            pst.executeUpdate();
            sql="update couponhold set hold_mount=hold_mount-1 where coup_id=?";
            pst=conn.prepareStatement(sql);
            pst.setInt(1,coupid);
            pst.executeUpdate();
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
        return keyID;
	}
	
	@Override
	public float settle(int coupid) throws BaseException{
		Connection conn=null;
        float result = 0;
        try {
            conn=DBUtil.getConnection();
            String sql="select coup_count from coupon where coup_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,coupid);
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
            	result=rs.getFloat(1);
            }
			return result;
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
		return result;
	}
	
	@Override
	public float searchorigin(int orderid) throws BaseException{
		Connection conn=null;
        float result = 0;
        try {
            conn=DBUtil.getConnection();
            String sql="select originprice from productorder where order_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,orderid);
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
            	result=rs.getFloat(1);
            }
			return result;
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
		return result;
	}
	
	@Override
	public float searchfinal(int orderid) throws BaseException{
		Connection conn=null;
        float result = 0;
        try {
            conn=DBUtil.getConnection();
            String sql="select finalprice from productorder where order_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,orderid);
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
            	result=rs.getFloat(1);
            }
			return result;
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
		return result;
	}
	
	@Override
	public void addToOrderdetails(List<BeanProduct> cartList, int key) throws BaseException{
		Connection conn=null;
        try {
            conn=DBUtil.getConnection();
            for(int i=0;i<cartList.size();i++) {
            	String sql="insert into orderdetail(order_id,pro_id,mount,price,perdiscount) values(?,?,?,?,?)";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1,key);
                pst.setInt(2,cartList.get(i).getPro_id());
                pst.setInt(3,cartList.get(i).getCount());
                pst.setFloat(4,cartList.get(i).getPro_price());
                pst.setFloat(5,cartList.get(i).getPro_discount());
                pst.executeUpdate();
    			pst.close();
            }
            for(int j=0;j<cartList.size();j++) {
            	String sql="update shopkeeper set total_sale=total_sale+? where shop_id=?";
                java.sql.PreparedStatement pst=conn.prepareStatement(sql);
                pst.setInt(1,cartList.get(j).getCount());
                pst.setInt(2,cartList.get(j).getShop_id());
                pst.executeUpdate();
    			pst.close();
            }
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
	public BeanPreferential manSet(float finalprice) throws BaseException{
		Connection conn=null;
        BeanPreferential result = new BeanPreferential();
        int count=0;
        try {
            conn=DBUtil.getConnection();
            String sql="select pre_id,pre_require,ifcoupon,pre_cut,shop_id from preferential order by pre_require";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            ResultSet rs=pst.executeQuery();
            while(rs.next()&&count==0) {
            	if(rs.getFloat(2)>finalprice&&rs.getString(3).equals("是")) {
            		sql="update productorder set pre_id=? where order_id=?";
                    pst=conn.prepareStatement(sql);
                    pst.setInt(1,rs.getInt(1));
                    pst.setInt(2,keyID);
                    pst.executeUpdate();
        			pst.close();
        			result.setPre_id(rs.getInt(1));
        			result.setPre_require(rs.getFloat(2));
        			result.setIfcoupon(rs.getString(3));
        			result.setPre_cut(rs.getFloat(4));
        			result.setShop_id(rs.getInt(5));
        			count++;
            	}else {
            		result.setPre_cut(0);
            		result.setPre_id(-1);
        			result.setPre_require(-1);
        			result.setIfcoupon("否");
        			result.setShop_id(-1);
            	}
            }
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
		return result;
	}
	
}
