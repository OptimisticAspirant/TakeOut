package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanRider;
import cn.edu.zucc.takeout.model.BeanRiderbill;
import cn.edu.zucc.takeout.util.BaseException;

public interface IRiderbillManager {

	public List<BeanRiderbill> loadriderbill(BeanRider rider) throws BaseException;

}
