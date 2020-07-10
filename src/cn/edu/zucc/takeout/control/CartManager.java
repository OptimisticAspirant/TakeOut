package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.zucc.takeout.itf.ICartManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.DBUtil;

public class CartManager implements ICartManager{
	
	@Override
	public void settle(BeanProduct shop,BeanCustomer cust,int coupid, int addressid,float orginprice,float finalprice,Date requiretime) throws BaseException{
		Connection conn=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = df.format(System.currentTimeMillis());
		Timestamp createTime = Timestamp.valueOf(time);
        try {
            conn=DBUtil.getConnection();
            String sql="insert into productorder(shop_id,cust_id,coup_id,add_id,originprice,finalprice,starttime,requiretime) values(?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
            pst.setInt(2,cust.getCust_id());
            pst.setInt(3,coupid);
            pst.setInt(4,addressid);
            pst.setFloat(5,orginprice);
            pst.setFloat(6,finalprice);
            pst.setTimestamp(7,createTime);
            pst.setTimestamp(8,new java.sql.Timestamp(requiretime.getTime()));
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
	
	
	
}
