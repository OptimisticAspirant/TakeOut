package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeout.itf.IRiderManager;
import cn.edu.zucc.takeout.model.BeanRider;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class RiderManager implements IRiderManager{
	@Override
	public BeanRider addrider(String ridername) throws BaseException{
		BeanRider result=new BeanRider();
        Connection conn=null;
        if(ridername.equals("")) {
        	throw new BusinessException("�뽫��Ϣ��д������");
        }
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = df.format(System.currentTimeMillis());
		Timestamp createDate = Timestamp.valueOf(time);
        try {
            conn=DBUtil.getConnection();
            String sql="insert into rider(rider_name,entrydate,identity) values(?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,ridername);
            pst.setTimestamp(2,createDate);
            pst.setString(3, "����");
            pst.execute();
			pst.close();
			result.setRider_name(ridername);
			result.setEntrydate(createDate);
			result.setIdentity("����");
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
	public void deleterider(BeanRider rider) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from riderbill where rider_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,rider.getRider_id());
			pst.execute();
			sql="delete from rider where rider_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,rider.getRider_id());
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
	public void modifyrider(BeanRider rider,String name) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update rider set rider_name=? where rider_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setInt(2,rider.getRider_id());
			pst.executeUpdate();
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
	public List<BeanRider> loadAll() throws BaseException{
		List<BeanRider> result=new ArrayList<BeanRider>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select rider_id,rider_name,entrydate,identity from rider";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanRider p=new BeanRider();
				p.setRider_id(rs.getInt(1));
				p.setRider_name(rs.getString(2));
				p.setEntrydate(rs.getDate(3));
				p.setIdentity(rs.getString(4));
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

	public int riderSumCount(BeanRider rider) {
		int count=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select count(*) from riderbill group by rider_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				count=rs.getInt(1);
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
		return count;
	}

	public float riderSumIncome(BeanRider rider) {
		float money=0;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select sum(income) from riderbill group by rider_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				money=rs.getInt(1);
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
		return money;
	}
}
