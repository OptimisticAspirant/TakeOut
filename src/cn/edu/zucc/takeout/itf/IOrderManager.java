package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanOrderdetail;
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.model.BeanRider;
import cn.edu.zucc.takeout.util.BaseException;

public interface IOrderManager {

	public List<BeanProductorder> loadOrders(BeanCustomer customer) throws BaseException;

	public List<BeanOrderdetail> loadDetails(BeanProductorder productorder) throws BaseException;

	public void evaluateRider(BeanProductorder order, String evaluate) throws BaseException;

	public void evaluateProduct(BeanOrderdetail product, String content, float start) throws BaseException;

	public void deleteorders(BeanProductorder order) throws BaseException;

	public void assignOrder(BeanRider rider, BeanProductorder order) throws BaseException;

	public List<BeanProductorder> loadAllOrders() throws BaseException;

	public void receiveorders(BeanProductorder order) throws BaseException;

}
