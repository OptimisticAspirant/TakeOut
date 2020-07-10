package cn.edu.zucc.takeout.itf;

import java.util.Date;

import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.util.BaseException;

public interface ICartManager {

	public void settle(BeanProduct shop, BeanCustomer cust, int coupid, int addressid, float orginprice, float finalprice,
			Date requiretime) throws BaseException;

}
