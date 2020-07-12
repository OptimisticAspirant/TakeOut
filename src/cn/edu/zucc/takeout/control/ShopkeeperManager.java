package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.IShopkeeperManager;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class ShopkeeperManager implements IShopkeeperManager{
	
	@Override
	public BeanShopkeeper addshop(String shopname,int totalsale) throws BaseException{
		BeanShopkeeper result=new BeanShopkeeper();
        Connection conn=null;
        if(shopname.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="insert into shopkeeper(shop_name,total_sale) values(?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql); 
            pst.setString(1,shopname);
            pst.setInt(2,totalsale);
            pst.execute();
			pst.close();
			result.setShop_name(shopname);
			result.setTotal_sale(totalsale);
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
	public void deleteshop(BeanShopkeeper shop) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="delete from coupon where shop_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
			pst.executeUpdate();
			sql="delete from couponhold where shop_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
			pst.executeUpdate();
			sql="delete from discount where shop_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
			pst.executeUpdate();
			sql="delete from preferential where shop_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
			pst.executeUpdate();
			sql="delete from product where shop_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
			pst.executeUpdate();
			sql="delete from productevaluate where shop_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
			pst.executeUpdate();
			sql="delete from productorder where shop_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
			pst.executeUpdate();
			sql="delete from shopkeeper where shop_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,shop.getShop_id());
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
	public List<BeanShopkeeper> loadAll() throws BaseException{
		List<BeanShopkeeper> result=new ArrayList<BeanShopkeeper>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select shop_id,shop_name,shop_star,per_consume,total_sale from shopkeeper";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanShopkeeper p=new BeanShopkeeper();
				p.setShop_id(rs.getInt(1));
				p.setShop_name(rs.getString(2));
				p.setShop_star(rs.getFloat(3));
				p.setPer_consume(rs.getFloat(4));
				p.setTotal_sale(rs.getInt(5));
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
	public void perconsume(BeanShopkeeper shop) throws BaseException{
		Connection conn=null;
		float money=0;
        try {
            conn=DBUtil.getConnection();
            String sql="select avg(finalprice) from productorder where shop_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql); 
            pst.setInt(1,shop.getShop_id());
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
            	money=rs.getFloat(1);
            }
			pst.close();
			sql="update shopkeeper set per_consume=? where shop_id=?";
            pst=conn.prepareStatement(sql); 
            pst.setFloat(1,money);
            pst.setInt(2,shop.getShop_id());
            pst.executeUpdate();
			pst.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null) {
                try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
	}
	@Override
	public void shopstar(BeanShopkeeper shop) throws BaseException{
		Connection conn=null;
		float shopstar=0;
        try {
            conn=DBUtil.getConnection();
            String sql="select avg(star) from productevaluate where shop_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql); 
            pst.setInt(1,shop.getShop_id());
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
            	shopstar=rs.getFloat(1);
            }
			pst.close();
			sql="update shopkeeper set shop_star=? where shop_id=?";
            pst=conn.prepareStatement(sql); 
            pst.setFloat(1,shopstar);
            pst.setInt(2,shop.getShop_id());
            pst.executeUpdate();
			pst.close();
        }catch(SQLException e) {
            e.printStackTrace();
        }finally {
            if(conn!=null) {
                try {
					conn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        }
	}
}
