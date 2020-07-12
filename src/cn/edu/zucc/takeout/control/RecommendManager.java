package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.IRecommendManager;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.DBUtil;

public class RecommendManager implements IRecommendManager{
	
	@Override
	public List<BeanProduct> loadPros() throws BaseException{
		List<BeanProduct> result=new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pro_id,shop_id,cate_id,pro_name,pro_price,pro_discount from product order by cate_id limit 5";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
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
