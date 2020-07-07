package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public interface IProductManager {
	
	public void add(BeanShopkeeper shop, String id, String category,String name,Float price,Float discount) throws BaseException;
	
	public void deleteProduct(BeanProduct product) throws BaseException;

	List<BeanProduct> loadShopProducts(BeanShopkeeper shop) throws BaseException;

	public void modify(BeanProduct pro, String cateid) throws BaseException;
	
	public List<String> loadProCate() throws BaseException;

	public List<String> loadProCateID() throws BaseException;
	
}
