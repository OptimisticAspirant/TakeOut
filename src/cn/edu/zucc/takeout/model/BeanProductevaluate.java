package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanProductevaluate {
	private int shop_id;
	private int pro_id;
	private int cust_id;
	private String content;
	private Date eval_date;
	private float star;
	private String photo;
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getEval_date() {
		return eval_date;
	}
	public void setEval_date(Date eval_date) {
		this.eval_date = eval_date;
	}
	public float getStar() {
		return star;
	}
	public void setStar(float star) {
		this.star = star;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
}
