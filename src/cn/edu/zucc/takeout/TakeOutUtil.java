package cn.edu.zucc.takeout;

import cn.edu.zucc.takeout.control.CategoryManager;
import cn.edu.zucc.takeout.control.CustomerManager;
import cn.edu.zucc.takeout.control.ManagerManager;
import cn.edu.zucc.takeout.control.ProductManager;
import cn.edu.zucc.takeout.control.RiderManager;
import cn.edu.zucc.takeout.control.ShopkeeperManager;
import cn.edu.zucc.takeout.itf.ICategoryManager;
import cn.edu.zucc.takeout.itf.ICustomerManager;
import cn.edu.zucc.takeout.itf.IManagerManager;
import cn.edu.zucc.takeout.itf.IProductManager;
import cn.edu.zucc.takeout.itf.IRiderManager;
import cn.edu.zucc.takeout.itf.IShopkeeperManager;

public class TakeOutUtil {
	
	public static IManagerManager managerManager=new ManagerManager();
	public static IRiderManager riderManager=new RiderManager();
	public static IShopkeeperManager shopkeeperManager=new ShopkeeperManager();
	public static ICustomerManager customerManager=new CustomerManager();
	public static ICategoryManager categoryManager=new CategoryManager();
	public static IProductManager productManager=new ProductManager();
}
