package cn.edu.zucc.takeout.itf;

import java.util.Date;
import java.util.List;

import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public interface ICartManager {

	public int settle(BeanShopkeeper shop, BeanCustomer cust, int coupid, int addressid, float originprice, float finalprice,
			Date requiretime) throws BaseException;

	public float settle(int coupid) throws BaseException;

	public float searchorigin(int orderid) throws BaseException;

	public float searchfinal(int orderid) throws BaseException;

	public void addToProduct(List<BeanProduct> cartList, int key) throws BaseException;

}
