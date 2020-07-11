package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeout.itf.ICustomerManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class CustomerManager implements ICustomerManager{
	
	@Override
	public BeanCustomer reg(int userid, String username, String gender,String phonenumber,String mail,String city,String pwd,String pwd2) throws BaseException{
		BeanCustomer result=new BeanCustomer();
        Connection conn=null;SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String time = df.format(System.currentTimeMillis());
		Timestamp createDate = Timestamp.valueOf(time);
		if(username.equals("")||gender.equals("")||phonenumber.equals("")||mail.equals("")||city.equals("")||pwd.equals("")||pwd2.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="select count(*) from customer where cust_id='"+userid+"'";
            java.sql.PreparedStatement st=conn.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()) {
            	if(rs.getInt(1)!=0) {
                    throw new BusinessException("该用户已存在");
                }
            }
            if(!pwd.equals(pwd2)) {
                throw new BusinessException("两次密码不匹配");
            }
            if(!gender.equals("男")&&!gender.equals("女")) {
            	throw new BusinessException("请在性别栏输入：“女” or “男”");
            }
            sql="insert into customer(cust_id,cust_name,cust_gender,cust_password,cust_phone,cust_mail,cust_city,rig_time,ifVip) values(?,?,?,?,?,?,?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,userid);
            pst.setString(2,username);
            pst.setString(3,gender);
            pst.setString(4,pwd);
            pst.setString(5,phonenumber);
            pst.setString(6,mail);
            pst.setString(7,city);
            pst.setTimestamp(8,createDate);
            pst.setString(9, "否");
            pst.execute();
			pst.close();
			result.setCust_id(userid);
			result.setCust_name(username);
			result.setCust_gender(gender);
			result.setCust_password(pwd);
			result.setCust_phone(phonenumber);
			result.setCust_mail(mail);
			result.setCust_city(city);
			result.setRig_time(createDate);
			result.setIfVIP("否");
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
	public BeanCustomer login(int userid,String pwd)throws BaseException{
		BeanCustomer result=new BeanCustomer();
        Connection conn=null;
        if(pwd.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="select cust_id,cust_name,cust_gender,cust_password,cust_phone,cust_mail,cust_city,rig_time,ifVip,VIPdeadline from customer where cust_id='"+userid+"'";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            if(!rs.next()) {
                throw new BusinessException("用户不存在");
            }
            if(!rs.getString(4).equals(pwd)) {
                throw new BusinessException("密码错误");
            }
            result.setCust_id(rs.getInt(1));
            result.setCust_name(rs.getString(2));
            result.setCust_gender(rs.getString(3));
            result.setCust_password(rs.getString(4));
            result.setCust_phone(rs.getString(5));
            result.setCust_mail(rs.getString(6));
            result.setCust_city(rs.getString(7));
            result.setRig_time(rs.getTimestamp(8));
            result.setIfVIP(rs.getString(9));
            result.setvIPdeadline(rs.getTimestamp(10));
            rs.close();
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
	public void changePwd(BeanCustomer user, String oldPwd,String newPwd, String newPwd2)throws BaseException{
		Connection conn=null;
		if(oldPwd.equals("")||newPwd.equals("")||newPwd2.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
		if(!oldPwd.equals(user.getCust_password())) throw new BusinessException("原密码错误");
		if(!newPwd.equals(newPwd2)) throw new BusinessException("两次密码不匹配");
		try {
			conn=DBUtil.getConnection();
			String sql="update customer set cust_password=? where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, user.getCust_id());
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
	public List<BeanCustomer> loadAll() throws BaseException{
		List<BeanCustomer> result=new ArrayList<BeanCustomer>();
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select cust_id,cust_name,cust_gender,cust_password,cust_phone,cust_mail,cust_city,rig_time,ifVIP,VIPdeadline from customer";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.execute();
			java.sql.ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				BeanCustomer p=new BeanCustomer();
				p.setCust_id(rs.getInt(1));
				p.setCust_name(rs.getString(2));
				p.setCust_gender(rs.getString(3));
				p.setCust_password(rs.getString(4));
				p.setCust_phone(rs.getString(5));
				p.setCust_mail(rs.getString(6));
				p.setCust_city(rs.getString(7));
				p.setRig_time(rs.getTimestamp(8));
				p.setIfVIP(rs.getString(9));
				p.setvIPdeadline(rs.getTimestamp(10));
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
	public void deleteuser(BeanCustomer customer) throws BaseException{
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select orderstate from productorder where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,customer.getCust_id());
			pst.execute();
			java.sql.ResultSet rs=pst.getResultSet();
			String state=null;
			if(rs.next()) {
				state=rs.getString(1);
				throw new BusinessException("该用户存在配送中的订单，不能删除");
			}
			sql="delete from customer where cust_id=?";
			pst=conn.prepareStatement(sql);
            pst.setInt(1,customer.getCust_id());
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
	public void modify(BeanCustomer customer,String username, String gender, String phonenumber, String mail, String city) throws BaseException{
		Connection conn=null;
		if(username.equals("")||username.equals("")||username.equals("")||username.equals("")||username.equals("")) {
			throw new BusinessException("请将信息填写完整！");
		}
		try {
			conn=DBUtil.getConnection();
			String sql="update customer set cust_name=? where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,username);
            pst.setInt(2,customer.getCust_id());
			pst.executeUpdate();
			pst.close();
			sql="update customer set cust_gender=? where cust_id=?";
			pst=conn.prepareStatement(sql);
            pst.setString(1,gender);
            pst.setInt(2,customer.getCust_id());
			pst.executeUpdate();
			pst.close();
			sql="update customer set cust_phone=? where cust_id=?";
			pst=conn.prepareStatement(sql);
            pst.setString(1,phonenumber);
            pst.setInt(2,customer.getCust_id());
			pst.executeUpdate();
			pst.close();
			sql="update customer set cust_mail=? where cust_id=?";
			pst=conn.prepareStatement(sql);
            pst.setString(1,mail);
            pst.setInt(2,customer.getCust_id());
			pst.executeUpdate();
			pst.close();
			sql="update customer set cust_city=? where cust_id=?";
			pst=conn.prepareStatement(sql);
            pst.setString(1,city);
            pst.setInt(2,customer.getCust_id());
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
	public String VipInfomation(BeanCustomer customer) throws BaseException{
		String info=null;
		Connection conn=null;
		try {
			conn=DBUtil.getConnection();
			String sql="select ifVIP from customer where cust_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1,customer.getCust_id());
			pst.execute();
			java.sql.ResultSet rs=pst.getResultSet();
			String ifVIP=null;
			if(rs.next()) {
				ifVIP=rs.getString(1);
			}
			if(ifVIP.equals("是")) {
				sql="select VIPdeadline from customer where cust_id=?";
				pst=conn.prepareStatement(sql);
	            pst.setInt(1,customer.getCust_id());
				pst.execute();
				rs=pst.getResultSet();
				Date deadline=null;
				if(rs.next()) {
					deadline=rs.getDate(1);
				}
				return "您的会员有效期截至"+deadline+" !";
			}else {
				return "您还不是会员！ 您可点击菜单栏“会员注册”按钮进行会员申请！下次登录时可查看会员截止日期！";
			}
			
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
		return info;
	}
	
}
