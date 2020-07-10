package cn.edu.zucc.takeout.model;

public class BeanProduct {
	public static final String[] tableTitles={"商品编号","商品分类","商品名称","商品价格","优惠价格"};
	public String getCell(int col){
		if(col==0) return pro_id+"";
		else if(col==1) return cate_id+"";
		else if(col==2) return pro_name;
		else if(col==3) return pro_price+"";
		else if(col==4) return pro_discount+"";
		else if(col==5) return count+"";
		else return "";
	}
	private int pro_id;
	private int shop_id;
	private int cate_id;
	private String pro_name;
	private float pro_price;
	private float pro_discount;
	private int count=1;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getPro_id() {
		return pro_id;
	}
	public void setPro_id(int pro_id) {
		this.pro_id = pro_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public float getPro_price() {
		return pro_price;
	}
	public void setPro_price(float pro_price) {
		this.pro_price = pro_price;
	}
	public float getPro_discount() {
		return pro_discount;
	}
	public void setPro_discount(float pro_discount) {
		this.pro_discount = pro_discount;
	}
}
