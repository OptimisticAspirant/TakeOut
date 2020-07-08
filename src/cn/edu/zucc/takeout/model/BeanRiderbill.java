package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanRiderbill {
	private int rider_id;
	private int order_id;
	private Date taketime;
	private String evaluate;
	private float income;
	public int getRider_id() {
		return rider_id;
	}
	public void setRider_id(int rider_id) {
		this.rider_id = rider_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public Date getTaketime() {
		return taketime;
	}
	public void setTaketime(Date taketime) {
		this.taketime = taketime;
	}
	public String getEvaluate() {
		return evaluate;
	}
	public void setEvaluate(String evaluate) {
		this.evaluate = evaluate;
	}
	public float getIncome() {
		return income;
	}
	public void setIncome(float income) {
		this.income = income;
	}
}
