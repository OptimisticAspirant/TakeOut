package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanProductcategory;
import cn.edu.zucc.takeout.util.BaseException;

public interface ICategoryManager {
	
	public BeanProductcategory addcate(String name) throws BaseException;
	
	public void deletecate(BeanProductcategory cate) throws BaseException;
	
	public List<BeanProductcategory> loadAll() throws BaseException;

	public void modifycate(BeanProductcategory cate, String name) throws BaseException;
	
}
