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
	public BeanManager reg(int managerid,String username,String pwd,String pwd2) throws BaseException {
		BeanManager result=new BeanManager();
        Connection conn=null;
        if(username.equals("")||pwd.equals("")||pwd2.equals("")) {
        	throw new BusinessException("�뽫��Ϣ��д������");
        }
        if(!pwd.equals(pwd2)) {
            throw new BusinessException("�������벻ƥ��");
        }
        try {
        	conn=DBUtil.getConnection();
            String sql="select count(*) from manager where manager_id=?";
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.setInt(1, managerid);
            ResultSet rs=pst.executeQuery();
            while(rs.next()) {
            	if(rs.getInt(1)!=0) {
                    throw new BusinessException("�ù���Ա�Ѵ���");
                }
            }
            if(!pwd.equals(pwd2)) {
                throw new BusinessException("�������벻ƥ��");
            }
            sql="insert into manager(manager_id,manager_name,manager_password) values(?,?,?)";
            pst=conn.prepareStatement(sql);
            pst.setInt(1, managerid);
            pst.setString(2,username);
            pst.setString(3,pwd);
            pst.execute();
			pst.close();
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
	public BeanManager login(int userid, String pwd) throws BaseException {
		BeanManager result=new BeanManager();
        Connection conn=null;
        if(pwd.equals("")) {
        	throw new BusinessException("�뽫��Ϣ��д������");
        }
        try {
            conn=DBUtil.getConnection();
            String sql="select manager_id,manager_name,manager_password from manager where manager_id='"+userid+"'";
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery(sql);
            if(!rs.next()) {
                throw new BusinessException("�û�������");
            }
            if(!rs.getString(3).equals(pwd)) {
                throw new BusinessException("�������");
            }
            result.setManager_id(rs.getInt(1));
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
        	throw new BusinessException("�뽫��Ϣ��д������");
        }
		try {
			conn=DBUtil.getConnection();
			String sql="select * from manager where manager_id=?";
			java.sql.PreparedStatement pst=conn.prepareStatement(sql);
			pst.setInt(1,user.getManager_id());
			java.sql.ResultSet rs=pst.executeQuery();
			if(!rs.next()) throw new BusinessException("��½�˺Ų�����");
			if(!oldPwd.equals(user.getManager_password())) throw new BusinessException("ԭ�������");
			if(!newPwd.equals(newPwd2)) throw new BusinessException("�������벻ƥ��");
			rs.close();
			pst.close();
			sql="update manager set manager_password=? where manager_id=?";
			pst=conn.prepareStatement(sql);
			pst.setString(1, newPwd);
			pst.setInt(2, user.getManager_id());
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
