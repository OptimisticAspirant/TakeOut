package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cn.edu.zucc.takeout.itf.IAddressManager;
import cn.edu.zucc.takeout.model.BeanAddress;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class AddressManager implements IAddressManager{
	
	@Override
	public void addaddress(BeanCustomer customer,String province,String city,String area,String location,String contacts,String phonenumber) throws BaseException{
        Connection conn=null;
        if(province.equals("")||city.equals("")||area.equals("")||location.equals("")||contacts.equals("")||phonenumber.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="insert into address(cust_id,province,city,area,location,contacts,phonenumber) values(?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,customer.getCust_id());
            pst.setString(2,province);
            pst.setString(3,city);
            pst.setString(4,area);
            pst.setString(5,location);
            pst.setString(6,contacts);
            pst.setString(7,phonenumber);
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
	public void deleteaddress(BeanAddress address) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select * from productorder where add_id=? and orderstate=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, address.getAdd_id());
			pst.setString(2, "配送中");
			pst.execute();
			ResultSet rs=pst.getResultSet();
			if(rs.next()) throw new BusinessException("该地址还有配送中的订单，不能删除!");
			rs.close();
			pst.close();
			sql="delete from address where add_id=?";
			pst=conn.prepareStatement(sql);
			pst.setInt(1, address.getAdd_id());
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
	public List<BeanAddress> loadCusAddresses(BeanCustomer customer) throws BaseException{
		List<BeanAddress> result=new ArrayList<BeanAddress>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select add_id,cust_id,province,city,area,location,contacts,phonenumber from address where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1, customer.getCust_id());
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanAddress p=new BeanAddress();
				p.setAdd_id(rs.getInt(1));
				p.setCust_id(rs.getInt(2));
				p.setProvince(rs.getString(3));
				p.setCity(rs.getString(4));
				p.setArea(rs.getString(5));
				p.setLocation(rs.getString(6));
				p.setContacts(rs.getString(7));
				p.setPhonenumber(rs.getString(8));
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
