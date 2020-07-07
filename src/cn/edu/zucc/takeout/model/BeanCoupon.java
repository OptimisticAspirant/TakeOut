package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanCoupon {
	public static final String[] tableTitles={"优惠券编号","优惠金额","集单要求数","开始时间","失效时间"};
	public String getCell(int col){
		if(col==0) return coup_id;
		else if(col==1) return coup_amount+"";
		else if(col==2) return coup_count+"";
		else if(col==3) return startdate+"";
		else if(col==4) return enddate+"";
		else return "";
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
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
