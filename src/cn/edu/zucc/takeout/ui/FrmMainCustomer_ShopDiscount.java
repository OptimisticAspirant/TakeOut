package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanCoupon;
import cn.edu.zucc.takeout.model.BeanDiscount;
import cn.edu.zucc.takeout.model.BeanPreferential;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer_ShopDiscount extends JFrame{
	private static final long serialVersionUID = 1L;
	
	private Object tblCouTitle[]=BeanCoupon.tableTitles;
	private Object tblCouData[][];
	DefaultTableModel tabCouModel=new DefaultTableModel();
	private JTable dataTableCou=new JTable(tabCouModel);
	
	private Object tblManTitle[]=BeanPreferential.tableTitles;
	private Object tblManData[][];
	DefaultTableModel tabManModel=new DefaultTableModel();
	private JTable dataTableMan=new JTable(tabManModel);
	
	private BeanCoupon curCoupon=null;
	List<BeanCoupon> allCoupon=null;
	private BeanPreferential curMan=null;
	List<BeanPreferential> allMan=null;
	
	BeanShopkeeper shop = new FrmMainCustomer().shop;
	
	private void reloadCouTable(){
		try {
			allCoupon=TakeOutUtil.couponManager.loadShopCoupon(shop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCouData =  new Object[allCoupon.size()][BeanCoupon.tableTitles.length];
		for(int i=0;i<allCoupon.size();i++){
			for(int j=0;j<BeanCoupon.tableTitles.length;j++)
				tblCouData[i][j]=allCoupon.get(i).getCell(j);
		}
		tabCouModel.setDataVector(tblCouData,tblCouTitle);
		this.dataTableCou.validate();
		this.dataTableCou.repaint();
	}
	
	private void reloadManTable(){
		try {
			allMan=TakeOutUtil.couponManager.loadShopMan(shop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblManData =  new Object[allMan.size()][BeanDiscount.tableTitles.length];
		for(int i=0;i<allMan.size();i++){
			for(int j=0;j<BeanDiscount.tableTitles.length;j++)
				tblManData[i][j]=allMan.get(i).getCell(j);
		}
		tabManModel.setDataVector(tblManData,tblManTitle);
		this.dataTableMan.validate();
		this.dataTableMan.repaint();
	}
	
	public FrmMainCustomer_ShopDiscount() {
		
		reloadCouTable();
		reloadManTable();

		this.setTitle("商家优惠券及满减信息");
		this.setSize(900, 400);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
	    this.getContentPane().add(new JScrollPane(this.dataTableCou), BorderLayout.WEST);
	    this.getContentPane().add(new JScrollPane(this.dataTableMan), BorderLayout.CENTER);
	    
	}
	
}
