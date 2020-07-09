package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import cn.edu.zucc.takeout.itf.IVIPManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.DBUtil;

public class VIPManager implements IVIPManager{
	
	@Override
	public void VIPRegister(BeanCustomer customer,int length) throws BaseException{
		Connection conn=null;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = df.format(System.currentTimeMillis());
		Timestamp date = Timestamp.valueOf(time);
        try {
            conn=DBUtil.getConnection();
            String sql="select ifVIP from customer where cust_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,customer.getCust_id());
			pst.execute();
			ResultSet rs=pst.getResultSet();
			String origininfo=null;
			while(rs.next()) {
				origininfo=rs.getString(1);
			}
			rs.close();
			pst.close();
			if(origininfo.equals("·ñ")) {
				sql="update customer set ifVIP=? where cust_id=?";
	            pst=conn.prepareStatement(sql);
	            pst.setString(1,"ÊÇ");
	            pst.setInt(2,customer.getCust_id());
	            pst.execute();
				pst.close();
				sql="update customer set VIPdeadline=date_add(?, interval ? month) where cust_id=?";
	            pst=conn.prepareStatement(sql);
	            pst.setTimestamp(1,date);
	            pst.setInt(2,length);
	            pst.setInt(3,customer.getCust_id());
	            pst.execute();
				pst.close();
			}else if (origininfo.equals("ÊÇ")) {
	            sql="select VIPdeadline from customer where cust_id=?";
	            pst=conn.prepareStatement(sql);
	            pst.setInt(1,customer.getCust_id());
				pst.execute();
				rs=pst.getResultSet();
				Date ddddd=null;
				while(rs.next()) {
					ddddd=rs.getDate(1);
				}
				rs.close();
				pst.close();
				sql="update customer set VIPdeadline=date_add(ddddd, interval ? month) where cust_id=?";
	            pst=conn.prepareStatement(sql);
	            pst.setInt(1,length);
	            pst.setInt(2,customer.getCust_id());
	            pst.execute();
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
	
}
