package cn.edu.zucc.takeout.model;

public class BeanDiscount {
	public static final String[] tableTitles={"优惠券编号","商家编号","集单要求数","已定单数"};
	public String getCell(int col){
		if(col==0) return coup_id+"";
		else if(col==1) return shop_id+"";
		else if(col==2) return collect_require+"";
		else if(col==3) return collect_count+"";
		else return "";
	}
	private int cust_id;
	private int shop_id;
	private int coup_id;
	private int collect_require;
	private int collect_count;
	public int getCollect_require() {
		return collect_require;
	}
	public void setCollect_require(int collect_require) {
		this.collect_require = collect_require;
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public int getCoup_id() {
		return coup_id;
	}
	public void setCoup_id(int coup_id) {
		this.coup_id = coup_id;
	}
	public int getCollect_count() {
		return collect_count;
	}
	public void setCollect_count(int collect_count) {
		this.collect_count = collect_count;
	}
}
