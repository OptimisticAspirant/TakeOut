package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanCouponhold {
	private String coup_id;
	private String cust_id;
	private String shop_id;
	private int hold_mount;
	private Date hold_deadline;
	public String getCoup_id() {
		return coup_id;
	}
	public void setCoup_id(String coup_id) {
		this.coup_id = coup_id;
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
	public int getHold_mount() {
		return hold_mount;
	}
	public void setHold_mount(int hold_mount) {
		this.hold_mount = hold_mount;
	}
	public Date getHold_deadline() {
		return hold_deadline;
	}
	public void setHold_deadline(Date hold_deadline) {
		this.hold_deadline = hold_deadline;
	}
}
