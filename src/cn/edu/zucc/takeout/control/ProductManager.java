package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.IProductManager;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class ProductManager implements IProductManager{
	@Override
	public void add(BeanShopkeeper shop,int proid,String category,String name,Float price,Float discount) throws BaseException {
		Connection conn=null;
		if(price==null||discount==null||category.equals("")||name.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
		int cateid=0;
		try {
			conn=DBUtil.getConnection();
            String sql="select * from product where pro_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, proid);
			pst.execute();
			ResultSet rs=pst.getResultSet();
			if(rs.next()) throw new BusinessException("该商品编号已存在!");
			sql="insert into product(pro_id,shop_id,cate_id,pro_name,pro_price,pro_discount) values(?,?,?,?,?,?)";
			pst=conn.prepareStatement(sql); 
			pst.setInt(1, proid);
			pst.setInt(2, shop.getShop_id());
			pst.setString(3, category);
			pst.setString(4, name);
			pst.setFloat(5, price);
			pst.setFloat(6, discount);
			pst.execute();
			pst.close();
			sql="select cate_id from product where pro_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, proid);
			pst.execute();
			rs=pst.getResultSet();
			while(rs.next()) {
				cateid=rs.getInt(1);
			}
			pst.close();
			sql="update productcategory set pro_count=pro_count+1 where cate_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, cateid);
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
	public List<BeanProduct> loadShopProducts(BeanShopkeeper shop) throws BaseException {
		List<BeanProduct> result=new ArrayList<BeanProduct>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select pro_id,shop_id,cate_id,pro_name,pro_price,pro_discount from product where shop_id=? order by cate_id";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, shop.getShop_id());
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

	@Override
	public void deleteProduct(BeanProduct product) throws BaseException {
		Connection conn=null;
		String cateid=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select cate_id from product where pro_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, product.getPro_id());
			pst.execute();
			java.sql.ResultSet rs=pst.getResultSet();
			while(rs.next()) {
				cateid=rs.getString(1);
			}
			pst.close();
			sql="update productcategory set pro_count=pro_count-1 where cate_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, cateid);
			pst.executeUpdate();
			pst.close();
			sql="delete from product where pro_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, product.getPro_id());
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
	public void modify(BeanProduct pro,int cateid) throws BaseException {
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="update product set cate_id=? where pro_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, pro.getCate_id());
			pst.setInt(2, pro.getPro_id());
			pst.execute();
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
	public List<String> loadProCate() throws BaseException {
		List<String> result=new ArrayList<String>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select columnname from productcategory";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String s=rs.getString(1);
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
	public List<String> loadProCateID() throws BaseException {
		List<String> result=new ArrayList<String>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select cate_id from productcategory";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				String s=rs.getString(1);
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
