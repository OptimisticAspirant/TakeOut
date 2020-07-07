package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanProductorder {
	private String order_id;
	private String pre_id;
	private String add_id;
	private String cust_id;
	private String shop_id;
	private String coup_id;
	private String rider_id;
	private float originprice;
	private float finalprice;
	private Date starttime;
	private Date requiretime;
	private String orderstate;
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public String getPre_id() {
		return pre_id;
	}
	public void setPre_id(String pre_id) {
		this.pre_id = pre_id;
	}
	public String getAdd_id() {
		return add_id;
	}
	public void setAdd_id(String add_id) {
		this.add_id = add_id;
	}
	public String getCust_id() {
		return cust_id;
	}
	public void setCust_id(String cust_id) {
		this.cust_id = cust_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public String getCoup_id() {
		return coup_id;
	}
	public void setCoup_id(String coup_id) {
		this.coup_id = coup_id;
	}
	public String getRider_id() {
		return rider_id;
	}
	public void setRider_id(String rider_id) {
		this.rider_id = rider_id;
	}
	public float getOriginprice() {
		return originprice;
	}
	public void setOriginprice(float originprice) {
		this.originprice = originprice;
	}
	public float getFinalprice() {
		return finalprice;
	}
	public void setFinalprice(float finalprice) {
		this.finalprice = finalprice;
	}
	public Date getStarttime() {
		return starttime;
	}
	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}
	public Date getRequiretime() {
		return requiretime;
	}
	public void setRequiretime(Date requiretime) {
		this.requiretime = requiretime;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
}
