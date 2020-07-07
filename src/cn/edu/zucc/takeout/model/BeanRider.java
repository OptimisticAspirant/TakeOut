package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanRider {
	public static final String[] tableTitles={"编号","姓名","入职时间","身份"};
	public String getCell(int col){
		if(col==0) return rider_id+"";
		else if(col==1) return rider_name;
		else if(col==2) return entrydate+"";
		else if(col==3) return identity+"";
		else return "";
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	private String rider_id;
	private String rider_name;
	private Date entrydate;
	private String identity;
	public String getRider_id() {
		return rider_id;
	}
	public void setRider_id(String rider_id) {
		this.rider_id = rider_id;
	}
	public String getRider_name() {
		return rider_name;
	}
	public void setRider_name(String rider_name) {
		this.rider_name = rider_name;
	}
	public Date getEntrydate() {
		return entrydate;
	}
	public void setEntrydate(Date entrydate) {
		this.entrydate = entrydate;
	}
	public String getIdentity() {
		return identity;
	}
	public void setIdentity(String identity) {
		this.identity = identity;
	}
}
