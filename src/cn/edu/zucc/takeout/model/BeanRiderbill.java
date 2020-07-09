package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanRiderbill {
	public static final String[] tableTitles={"订单编号","接单时间","用户评价","本单收入"};
	public String getCell(int col){
		if(col==0) return order_id+"";
		else if(col==1) return taketime+"";
		else if(col==2) return evaluate+"";
		else if(col==3) return income+"";
		else return "";
	}
	private int rider_id;
	private int order_id;
	private Date taketime;
	public static String[] getTabletitles() {
		return tableTitles;
	}
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
