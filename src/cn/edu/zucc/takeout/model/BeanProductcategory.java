package cn.edu.zucc.takeout.model;

public class BeanProductcategory {
	public static final String[] tableTitles={"分类编号","分类名称","分类下的商品数量"};
	public String getCell(int col){
		if(col==0) return cate_id+"";
		else if(col==1) return columnname;
		else if(col==2) return pro_count+"";
		else return "";
	}
	public static String[] getTabletitles() {
		return tableTitles;
	}
	private int cate_id;
	private String columnname;
	private int pro_count;
	public int getCate_id() {
		return cate_id;
	}
	public void setCate_id(int cate_id) {
		this.cate_id = cate_id;
	}
	public int getPro_count() {
		return pro_count;
	}
	public void setPro_count(int count) {
		this.pro_count = count;
	}
	public String getColumnname() {
		return columnname;
	}
	public void setColumnname(String columnname) {
		this.columnname = columnname;
	}
}
