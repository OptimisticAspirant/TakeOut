package cn.edu.zucc.takeout.itf;

import cn.edu.zucc.takeout.model.BeanManager;
import cn.edu.zucc.takeout.util.BaseException;

public interface IManagerManager {

	public BeanManager reg(int managerid,String username, String pwd,String pwd2) throws BaseException;
	
	public BeanManager login(int userid,String pwd)throws BaseException;
	
	public void changePwd(BeanManager user, String oldPwd,String newPwd, String newPwd2)throws BaseException;
}
