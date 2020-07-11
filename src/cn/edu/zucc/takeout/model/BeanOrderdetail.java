package cn.edu.zucc.takeout.model;

public class BeanOrderdetail {
	public static final String[] tableTitles={"订单编号","商品编号","商品数量","商品价格","优惠后的单品价格"};
	public String getCell(int col){
		if(col==0) return order_id+"";
		else if(col==1) return pro_id+"";
		else if(col==2) return mount+"";
		else if(col==3) return price+"";
		else if(col==4) return perdiscount+"";
		else return "";
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
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
