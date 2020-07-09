package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.ICategoryManager;
import cn.edu.zucc.takeout.model.BeanProductcategory;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class CategoryManager implements ICategoryManager{
	
	@Override
	public BeanProductcategory addcate(String name) throws BaseException{
		BeanProductcategory result=new BeanProductcategory();
        Connection conn=null;
        if(name.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="insert into productcategory(columnname,pro_count) values(?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,name);
            pst.setInt(2,0);
            pst.execute();
			pst.close();
			result.setColumnname(name);
			result.setPro_count(0);
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
	public void deletecate(BeanProductcategory cate) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from product where cate_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, cate.getCate_id());
			pst.execute();
			ResultSet rs=pst.getResultSet();
			if(rs.next()) throw new BusinessException("该分类下存在商品，不能删除!");
			rs.close();
			pst.close();
			sql="delete from productcategory where cate_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cate.getCate_id());
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
	public List<BeanProductcategory> loadAll() throws BaseException{
		List<BeanProductcategory> result=new ArrayList<BeanProductcategory>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select cate_id,columnname,pro_count from productcategory order by cate_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanProductcategory p=new BeanProductcategory();
				p.setCate_id(rs.getInt(1));
				p.setColumnname(rs.getString(2));
				p.setPro_count(rs.getInt(3));
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
	public void modifycate(BeanProductcategory cate,String name) throws BaseException{
		Connection conn=null;
		if(name.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
		try {
			conn=DBUtil.getConnection();
			String sql="update productcategory set columnname=? where cate_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, name);
			pst.setInt(2, cate.getCate_id());
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
