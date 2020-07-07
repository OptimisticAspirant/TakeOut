package cn.edu.zucc.takeout.model;

public class BeanDiscount {
	private String cust_id;
	private String shop_id;
	private String coup_id;
	private int collect_count;
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
	public int getCollect_count() {
		return collect_count;
	}
	public void setCollect_count(int collect_count) {
		this.collect_count = collect_count;
	}
}
