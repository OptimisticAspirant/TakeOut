package cn.edu.zucc.takeout;

import cn.edu.zucc.takeout.control.AddressManager;
import cn.edu.zucc.takeout.control.CategoryManager;
import cn.edu.zucc.takeout.control.CouponManager;
import cn.edu.zucc.takeout.control.CustomerManager;
import cn.edu.zucc.takeout.control.ManagerManager;
import cn.edu.zucc.takeout.control.ProductManager;
import cn.edu.zucc.takeout.control.RiderManager;
import cn.edu.zucc.takeout.control.ShopkeeperManager;
import cn.edu.zucc.takeout.control.VIPManager;
import cn.edu.zucc.takeout.control.CartManager;

import cn.edu.zucc.takeout.itf.IAddressManager;
import cn.edu.zucc.takeout.itf.ICartManager;
import cn.edu.zucc.takeout.itf.ICategoryManager;
import cn.edu.zucc.takeout.itf.ICouponManager;
import cn.edu.zucc.takeout.itf.ICustomerManager;
import cn.edu.zucc.takeout.itf.IManagerManager;
import cn.edu.zucc.takeout.itf.IProductManager;
import cn.edu.zucc.takeout.itf.IRiderManager;
import cn.edu.zucc.takeout.itf.IShopkeeperManager;
import cn.edu.zucc.takeout.itf.IVIPManager;

public class TakeOutUtil {
	
	public static IManagerManager managerManager=new ManagerManager();
	public static IRiderManager riderManager=new RiderManager();
	public static IShopkeeperManager shopkeeperManager=new ShopkeeperManager();
	public static ICustomerManager customerManager=new CustomerManager();
	public static ICategoryManager categoryManager=new CategoryManager();
	public static IProductManager productManager=new ProductManager();
	public static ICouponManager couponManager=new CouponManager();
	public static IAddressManager addressManager=new AddressManager();
	public static IVIPManager VIPManager=new VIPManager();
	public static ICartManager CartManager=new CartManager(); 
	
}
