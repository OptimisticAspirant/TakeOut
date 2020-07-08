package cn.edu.zucc.takeout.model;

public class BeanOrderdetial {
	private int pro_id;
	private int order_id;
	private int mount;
	private float price;
	private float perdiscount;
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	public int getMount() {
		return mount;
	}
	public void setMount(int mount) {
		this.mount = mount;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public float getPerdiscount() {
		return perdiscount;
	}
	public void setPerdiscount(float perdiscount) {
		this.perdiscount = perdiscount;
	}
}
