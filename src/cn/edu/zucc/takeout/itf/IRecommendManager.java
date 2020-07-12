package cn.edu.zucc.takeout.itf;

import java.util.List;

import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.util.BaseException;

public interface IRecommendManager {

	public List<BeanProduct> loadPros() throws BaseException;

}
