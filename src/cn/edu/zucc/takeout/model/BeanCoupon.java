package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanCoupon {
	private String coup_id;
	private String shop_id;
	private float coup_amount;
	private int coup_count;
	private Date startdate;
	private Date enddate;
	public String getCoup_id() {
		return coup_id;
	}
	public void setCoup_id(String coup_id) {
		this.coup_id = coup_id;
	}
	public String getShop_id() {
		return shop_id;
	}
	public void setShop_id(String shop_id) {
		this.shop_id = shop_id;
	}
	public float getCoup_amount() {
		return coup_amount;
	}
	public void setCoup_amount(float coup_amount) {
		this.coup_amount = coup_amount;
	}
	public int getCoup_count() {
		return coup_count;
	}
	public void setCoup_count(int coup_count) {
		this.coup_count = coup_count;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
}
