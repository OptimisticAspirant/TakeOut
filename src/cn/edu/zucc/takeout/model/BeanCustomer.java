package cn.edu.zucc.takeout.model;

import java.util.Date;

public class BeanCustomer {
	public static final String[] tableTitles={"用户编号","用户名称","用户性别","账号密码","用户手机","邮箱地址","所在城市","注册时间","是否会员","会员截止日期"};
	public String getCell(int col){
		if(col==0) return cust_id+"";
		else if(col==1) return cust_name;
		else if(col==2) return cust_gender;
		else if(col==3) return cust_password+"";
		else if(col==4) return cust_phone+"";
		else if(col==5) return cust_mail;
		else if(col==6) return cust_city+"";
		else if(col==7) return rig_time+"";
		else if(col==8) return ifVIP;
		else if(col==9) return vIPdeadline+"";
		else return "";
	}
	public static BeanCustomer currentLoginUser=null;
	private int cust_id;
	public static BeanCustomer getCurrentLoginUser() {
		return currentLoginUser;
	}
	public static void setCurrentLoginUser(BeanCustomer currentLoginUser) {
		BeanCustomer.currentLoginUser = currentLoginUser;
	}
	private String cust_name;
	private String cust_gender;
	private String cust_password;
	private String cust_phone;
	private String cust_mail;
	private String cust_city;
	private Date rig_time;
	private String ifVIP;
	private Date vIPdeadline;
	public int getCust_id() {
		return cust_id;
	}
	public void setCust_id(int cust_id) {
		this.cust_id = cust_id;
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	public String getCust_name() {
		return cust_name;
	}
	public void setCust_name(String cust_name) {
		this.cust_name = cust_name;
	}
	public String getCust_gender() {
		return cust_gender;
	}
	public void setCust_gender(String cust_gender) {
		this.cust_gender = cust_gender;
	}
	public String getCust_password() {
		return cust_password;
	}
	public void setCust_password(String cust_password) {
		this.cust_password = cust_password;
	}
	public String getCust_phone() {
		return cust_phone;
	}
	public void setCust_phone(String cust_phone) {
		this.cust_phone = cust_phone;
	}
	public String getCust_mail() {
		return cust_mail;
	}
	public void setCust_mail(String cust_mail) {
		this.cust_mail = cust_mail;
	}
	public String getCust_city() {
		return cust_city;
	}
	public void setCust_city(String cust_city) {
		this.cust_city = cust_city;
	}
	public Date getRig_time() {
		return rig_time;
	}
	public void setRig_time(Date rig_time) {
		this.rig_time = rig_time;
	}
	public String getIfVIP() {
		return ifVIP;
	}
	public void setIfVIP(String ifVIP) {
		this.ifVIP = ifVIP;
	}
	public Date getvIPdeadline() {
		return vIPdeadline;
	}
	public void setvIPdeadline(Date vIPdeadline) {
		this.vIPdeadline = vIPdeadline;
	}
}
