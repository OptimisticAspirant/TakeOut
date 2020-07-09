package cn.edu.zucc.takeout.model;

public class BeanAddress {
	public static final String[] tableTitles={"地址编号","省","市","区","地址","联系人","电话"};
	public String getCell(int col){
		if(col==0) return add_id+"";
		else if(col==1) return province+"";
		else if(col==2) return city+"";
		else if(col==3) return area+"";
		else if(col==4) return location+"";
		else if(col==5) return contacts+"";
		else if(col==6) return phonenumber+"";
		else return "";
	}
	private int add_id;
	private int cust_id;
	private String province;
	private String city;
	private String area;
	private String location;
	private String contacts;
	private String phonenumber;
	public int getAdd_id() {
		return add_id;
	}
	public void setAdd_id(int add_id) {
		this.add_id = add_id;
	}
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContacts() {
		return contacts;
	}
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
}
