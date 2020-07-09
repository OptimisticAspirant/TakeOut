package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public interface ICustomerManager {
	
	public BeanCustomer reg(int userid,String username, String gender,String phonenumber,String mail,String city,String pwd,String pwd2) throws BaseException;
	
	public BeanCustomer login(int userid,String pwd)throws BaseException;
	
	public void changePwd(BeanCustomer user, String oldPwd,String newPwd, String newPwd2)throws BaseException;

	public List<BeanCustomer> loadAll() throws BaseException;

	public void deleteuser(BeanCustomer customer) throws BaseException;

	public void modify(BeanCustomer customer,String username, String gender, String phonenumber, String mail, String city) throws BaseException;

	public String VipInfomation(BeanCustomer customer) throws BaseException;
	
}
