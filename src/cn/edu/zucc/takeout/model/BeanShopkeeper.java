package cn.edu.zucc.takeout.model;

public class BeanShopkeeper {
	public static final String[] tableTitles={"商家编号","商家名称","商家星级","人均消费","总销量"};
	public String getCell(int col){
		if(col==0) return shop_id+"";
		else if(col==1) return shop_name;
		else if(col==2) return shop_star+"";
		else if(col==3) return per_consume+"";
		else if(col==4) return total_sale+"";
		else return "";
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	private int shop_id;
	private String shop_name;
	private float shop_star;
	private float per_consume;
	private int total_sale;
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getTotal_sale() {
		return total_sale;
	}
	public void setTotal_sale(int totalsale) {
		this.total_sale = totalsale;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	public float getShop_star() {
		return shop_star;
	}
	public void setShop_star(float shop_star) {
		this.shop_star = shop_star;
	}
	public float getPer_consume() {
		return per_consume;
	}
	public void setPer_consume(float per_consume) {
		this.per_consume = per_consume;
	}
}
