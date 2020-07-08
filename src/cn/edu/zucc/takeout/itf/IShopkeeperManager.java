package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public interface IShopkeeperManager {
	
	public BeanShopkeeper addshop(String shopname,int totalsale) throws BaseException;
	
	public void deleteshop(BeanShopkeeper shop) throws BaseException;
	
	public List<BeanShopkeeper> loadAll() throws BaseException;
}
