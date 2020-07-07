package cn.edu.zucc.takeout.model;

public class BeanPreferential {
	private String pre_id;
	private String shop_id;
	private float pre_require;
	private float pre_cut;
	private String ifcoupon;
	public String getPre_id() {
		return pre_id;
	}
	public void setPre_id(String pre_id) {
		this.pre_id = pre_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public float getPre_require() {
		return pre_require;
	}
	public void setPre_require(float pre_require) {
		this.pre_require = pre_require;
	}
	public float getPre_cut() {
		return pre_cut;
	}
	public void setPre_cut(float pre_cut) {
		this.pre_cut = pre_cut;
	}
	public String getIfcoupon() {
		return ifcoupon;
	}
	public void setIfcoupon(String ifcoupon) {
		this.ifcoupon = ifcoupon;
	}
}
