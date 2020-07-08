package cn.edu.zucc.takeout.model;

public class BeanDiscount {
	public static final String[] tableTitles={"优惠券编号","商家编号","集单要求数","已定单数"};
	public String getCell(int col){
		if(col==0) return coup_id;
		else if(col==1) return shop_id+"";
		else if(col==2) return collect_require+"";
		else if(col==3) return collect_count+"";
		else return "";
	}
	private String cust_id;
	private String shop_id;
	private String coup_id;
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
	public String getCoup_id() {
		return coup_id;
	}
	public void setCoup_id(String coup_id) {
		this.coup_id = coup_id;
	}
	public int getCollect_count() {
		return collect_count;
	}
	public void setCollect_count(int collect_count) {
		this.collect_count = collect_count;
	}
}
