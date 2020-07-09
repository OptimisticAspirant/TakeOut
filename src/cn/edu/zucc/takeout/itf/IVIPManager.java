package cn.edu.zucc.takeout.itf;

import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public interface IVIPManager {

	public void VIPRegister(BeanCustomer customer, int length) throws BaseException;

}
