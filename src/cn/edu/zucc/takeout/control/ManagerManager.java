package cn.edu.zucc.takeout.control;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.edu.zucc.takeout.itf.IManagerManager;
import cn.edu.zucc.takeout.model.BeanManager;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;
import cn.edu.zucc.takeout.util.DBUtil;

public class ManagerManager implements IManagerManager{
	
	@Override
	public BeanManager reg(String userid,String username,String pwd,String pwd2) throws BaseException {
		BeanManager result=new BeanManager();
        Connection conn=null;
        if(userid.equals("")||username.equals("")||pwd.equals("")||pwd2.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="select count(*) from manager where manager_id='"+userid+"'";
            java.sql.PreparedStatement st=conn.prepareStatement(sql);
            ResultSet rs=st.executeQuery();
            while(rs.next()) {
            	if(rs.getInt(1)!=0) {
                    throw new BusinessException("该管理员已存在");
                }
            }
            if(!pwd.equals(pwd2)) {
                throw new BusinessException("两次密码不匹配");
            }
            sql="insert into manager(manager_id,manager_name,manager_password) values(?,?,?)";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setString(1,userid);
            pst.setString(2,username);
            pst.setString(3,pwd);
            pst.execute();
			pst.close();
			result.setManager_id(userid);
			result.setManager_name(username);
			result.setManager_password(pwd);
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
	public BeanManager login(String userid, String pwd) throws BaseException {
		BeanManager result=new BeanManager();
        Connection conn=null;
        if(userid.equals("")||pwd.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="select manager_id,manager_name,manager_password from manager where manager_id='"+userid+"'";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            if(!rs.next()) {
                throw new BusinessException("用户不存在");
            }
            if(!rs.getString(3).equals(pwd)) {
                throw new BusinessException("密码错误");
            }
            result.setManager_id(rs.getString(1));
            result.setManager_name(rs.getString(2));
            result.setManager_password(rs.getString(3));
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
	public void changePwd(BeanManager user, String oldPwd, String newPwd,String newPwd2) throws BaseException {
		Connection conn=null;
		if(oldPwd.equals("")||newPwd.equals("")||newPwd2.equals("")) {
        	throw new BusinessException("请将信息填写完整！");
        }
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manager where manager_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setString(1,user.getManager_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("登陆账号不存在");
			if(!oldPwd.equals(user.getManager_password())) throw new BusinessException("原密码错误");
			if(!newPwd.equals(newPwd2)) throw new BusinessException("两次密码不匹配");
			rs.close();
			pst.close();
			sql="update manager set manager_password=? where manager_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setString(2, user.getManager_id());
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
}
