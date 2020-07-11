package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.IRiderbillManager;
import cn.edu.zucc.takeout.model.BeanRider;
import cn.edu.zucc.takeout.model.BeanRiderbill;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.DBUtil;

public class RiderbillManager implements IRiderbillManager {

	@Override
	public List<BeanRiderbill> loadriderbill(BeanRider rider) throws BaseException{
		List<BeanRiderbill> result=new ArrayList<BeanRiderbill>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select order_id,taketime,evaluate,income from riderbill where rider_id=? order by order_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, rider.getRider_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanRiderbill s=new BeanRiderbill();
				s.setOrder_id(rs.getInt(1));
				s.setTaketime(rs.getDate(2));
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
	
}
