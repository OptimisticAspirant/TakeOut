package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanCoupon;
import cn.edu.zucc.takeout.model.BeanCouponhold;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanDiscount;
import cn.edu.zucc.takeout.model.BeanPreferential;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public interface ICouponManager {

	public void addcou(BeanShopkeeper shop,Float amount, int count, String start, String end)
			throws BaseException;

	public void deletecoup(BeanCoupon coup) throws BaseException;

	public List<BeanCoupon> loadShopCoupon(BeanShopkeeper shop) throws BaseException;
	
	public List<BeanPreferential> loadShopMan(BeanShopkeeper shop) throws BaseException;

	public List<BeanCouponhold> loadCouponhold(BeanCustomer customer, BeanShopkeeper shop) throws BaseException;

	public List<BeanDiscount> loadCollect(BeanCustomer customer) throws BaseException;

	public void changecoupon(BeanDiscount collect) throws BaseException;

	public void addpreferential(BeanShopkeeper shop, Float require, Float cut, String ifcoupon) throws BaseException;

	public void deletepreferential(BeanPreferential preferential) throws BaseException;

	public void deletecoupon(BeanCouponhold coupon) throws BaseException;

	public List<BeanCouponhold> loadCouponhold(BeanCustomer customer) throws BaseException;

}
