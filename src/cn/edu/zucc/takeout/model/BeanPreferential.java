package cn.edu.zucc.takeout.model;

public class BeanPreferential {
	
	public static final String[] tableTitles={"Âú¼õ±àºÅ","Âú¼õ½ğ¶î","ÓÅ»İ½ğ¶î","¿É·ñÓëÓÅ»İÈ¯µş¼Ó"};
	public String getCell(int col){
		if(col==0) return pre_id+"";
		else if(col==1) return pre_require+"";
		else if(col==2) return pre_cut+"";
		else if(col==3) return ifcoupon+"";
		else return "";
	}
	private int pre_id;
	private int shop_id;
	public static String[] getTabletitles() {
		return tableTitles;
	}
	private float pre_require;
	private float pre_cut;
	private String ifcoupon;
	public int getPre_id() {
		return pre_id;
	}
	public void setPre_id(int pre_id) {
		this.pre_id = pre_id;
	}
	public int getShop_id() {
		return shop_id;
	}
	public void setShop_id(int shop_id) {
		this.shop_id = shop_id;
	}
	public float getPre_require() {
		return pre_require;
	}
	public void setPre_require(float pre_require) {
		this.pre_require = pre_require;
	}
	public float getPre_cut() {
		return pre_cut;
	}
	public void setPre_cut(float pre_cut) {
		this.pre_cut = pre_cut;
	}
	public String getIfcoupon() {
		return ifcoupon;
	}
	public void setIfcoupon(String ifcoupon) {
		this.ifcoupon = ifcoupon;
	}
}
