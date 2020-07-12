package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanRiderbill;
import cn.edu.zucc.takeout.util.BaseException;

public interface ISearchManager {

	public List<BeanCustomer> loadCust(int custid) throws BaseException;

	public List<BeanRiderbill> loadRiderbill(int riderid) throws BaseException;

	public List<BeanProduct> loadPro(int proid) throws BaseException;

}
