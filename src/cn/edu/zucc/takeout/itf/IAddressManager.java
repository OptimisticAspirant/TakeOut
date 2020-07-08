package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanAddress;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public interface IAddressManager {
	
	public void addaddress(BeanCustomer customer,String province,String city,String area,String location,String contacts,String phonenumber) throws BaseException;

	public void deleteaddress(BeanAddress address) throws BaseException;

	public List<BeanAddress> loadCusAddresses(BeanCustomer customer) throws BaseException;

}
