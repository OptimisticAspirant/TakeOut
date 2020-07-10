package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanOrderdetail;
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.util.BaseException;

public interface IOrderManager {

	public List<BeanProductorder> loadOrders(BeanCustomer currentLoginUser) throws BaseException;

	public List<BeanOrderdetail> loadDetails(BeanProductorder productorder) throws BaseException;

}
