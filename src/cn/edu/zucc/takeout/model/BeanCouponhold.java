package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanCouponhold {
	public static final String[] tableTitles={"�Ż�ȯ���","�̼ұ��","�Żݽ��","ӵ������","ʧЧʱ��"};
	public String getCell(int col){
		if(col==0) return coup_id;
		else if(col==1) return shop_id+"";
		else if(col==2) return subtract+"";
		else if(col==3) return hold_mount+"";
		else if(col==4) return hold_deadline+"";
		else return "";
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	private String coup_id;
	private String cust_id;
	private String shop_id;
	private float subtract;
	public float getSubtract() {
		return subtract;
	}
	public void setSubtract(float subtract) {
		this.subtract = subtract;
	}
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
