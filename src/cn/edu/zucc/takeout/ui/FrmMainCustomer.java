package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.control.CustomerManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanOrderdetail;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanProductcategory;
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	private JPanel toolBar=new JPanel();

    private JButton btncart=new JButton("�鿴���ﳵ");
    private JButton btnselfinfo=new JButton("������Ϣ");
    private JButton btnaddress=new JButton("��ַ����");
    private JButton btncoupon=new JButton("�Ż�ȯ����");
    private JButton btnorders=new JButton("���ж���");
    private JButton btnmodifyPwd=new JButton("�����޸�");
    private JButton btnregister=new JButton("��Աע��");
    private JButton btnshopdiscount=new JButton("�鿴�̼��Ż���Ϣ");
    private JButton btnrecommend=new JButton("�鿴�Ƽ��˵�");
	private JButton btnaddcart=new JButton("���빺�ﳵ");
	public JLabel label=null;
	private JPanel statusBar = new JPanel();
	
	private Object tblShopTitle[]=BeanShopkeeper.tableTitles;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
	
	private Object tblCateTitle[]=BeanProductcategory.tableTitles;
	private Object tblCateData[][];
	DefaultTableModel tabCateModel=new DefaultTableModel();
	private JTable dataTableCate=new JTable(tabCateModel);
	
	private Object tblProTitle[]=BeanProduct.tableTitles;
	private Object tblProData[][];
	DefaultTableModel tabProModel=new DefaultTableModel();
	private JTable dataTablePro=new JTable(tabProModel);

	private BeanShopkeeper curShop=null;
	List<BeanShopkeeper> allShop=null;
	private BeanProductcategory curCate=null;
	List<BeanProductcategory> allCate=null;
	private BeanProduct curProduct=null;
	List<BeanProduct> allProduct=null;

	List<BeanProduct> shopPros=null;
	public static BeanShopkeeper shop=null;
	public static BeanProduct product=null;
	
	public static FrmMainCustomer_Cart frmMainCustomerCart=null;
	
	public static List<BeanProduct> cartList=new ArrayList<BeanProduct>();
	
	private void reloadShopTable(){
		try {
			allShop=TakeOutUtil.shopkeeperManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblShopData =  new Object[allShop.size()][BeanShopkeeper.tableTitles.length];
		for(int i=0;i<allShop.size();i++){
			for(int j=0;j<BeanShopkeeper.tableTitles.length;j++)
				tblShopData[i][j]=allShop.get(i).getCell(j);
		}
		tabShopModel.setDataVector(tblShopData,tblShopTitle);
		this.dataTableShop.validate();
		this.dataTableShop.repaint();
	}
	
	private void reloadCateTable(){
		try {
			allCate=TakeOutUtil.categoryManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCateData =  new Object[allCate.size()][BeanProductcategory.tableTitles.length];
		for(int i=0;i<allCate.size();i++){
			for(int j=0;j<BeanProductcategory.tableTitles.length;j++)
				tblCateData[i][j]=allCate.get(i).getCell(j);
		}
		tabCateModel.setDataVector(tblCateData,tblCateTitle);
		this.dataTableCate.validate();
		this.dataTableCate.repaint();
	}
	
	public void reloadProTable(){
		try {
			allProduct=TakeOutUtil.productManager.loadShopProducts(curShop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblProData =  new Object[allProduct.size()][BeanProduct.tableTitles.length];
		for(int i=0;i<allProduct.size();i++){
			for(int j=0;j<BeanProduct.tableTitles.length;j++)
				tblProData[i][j]=allProduct.get(i).getCell(j);
		}
		tabProModel.setDataVector(tblProData,tblProTitle);
		this.dataTablePro.validate();
		this.dataTablePro.repaint();
	}
	
	private void reloadShopProTabel(int shopIdx){
		if(shopIdx<0) return;
		curShop=allShop.get(shopIdx);
		try {
			shopPros=TakeOutUtil.productManager.loadShopProducts(curShop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblProData =new Object[shopPros.size()][tblProTitle.length];
		for(int i=0;i<shopPros.size();i++){
			for(int j=0;j<tblProTitle.length;j++)
				tblProData[i][j]=shopPros.get(i).getCell(j);
		}
		
		tabProModel.setDataVector(tblProData,tblProTitle);
		this.dataTablePro.validate();
		this.dataTablePro.repaint();
	}
	
	public FrmMainCustomer() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("��������-�û�");
    
	    this.btnselfinfo.addActionListener(this);
	    this.btnaddress.addActionListener(this);
	    this.btncoupon.addActionListener(this);
	    this.btnorders.addActionListener(this);
	    this.btnmodifyPwd.addActionListener(this);
	    this.btnregister.addActionListener(this);
	    this.btncart.addActionListener(this);
	    this.btnshopdiscount.addActionListener(this);
	    this.btnaddcart.addActionListener(this);
	    this.btnrecommend.addActionListener(this);
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnselfinfo);
	    toolBar.add(btnaddress);
	    toolBar.add(btncoupon);
	    toolBar.add(btnorders);
	    toolBar.add(btnmodifyPwd);
	    toolBar.add(btnregister);
	    toolBar.add(btnshopdiscount);
	    toolBar.add(btnrecommend);
	    toolBar.add(btnaddcart);
	    toolBar.add(btncart);
	    this.setJMenuBar(menubar);
	    
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    
		try {
			label = new JLabel("����!  "+BeanCustomer.currentLoginUser.getCust_name()+"!  "+new CustomerManager().VipInfomation(BeanCustomer.currentLoginUser));
		} catch (BaseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableShop), BorderLayout.WEST);
	    this.getContentPane().add(new JScrollPane(this.dataTableCate), BorderLayout.CENTER);
	    this.getContentPane().add(new JScrollPane(this.dataTablePro), BorderLayout.EAST);
	    
	    this.reloadShopTable();
	    this.reloadCateTable();
	    
		
	    this.dataTableShop.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer.this.dataTableShop.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMainCustomer.this.reloadShopProTabel(i);
			}
	    	
	    });
	    
	    this.dataTablePro.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer.this.dataTablePro.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnregister){
			FrmVIPRegister dlg=new FrmVIPRegister(this,"��Աע��",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btncart){
			FrmMainCustomer_Cart dlg=new FrmMainCustomer_Cart();
			frmMainCustomerCart=dlg;
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnmodifyPwd){
			FrmCustomerModifyPwd dlg=new FrmCustomerModifyPwd(this,"�����޸�",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btncoupon){
			FrmMainCustomer_Coupon dlg=new FrmMainCustomer_Coupon();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnaddress){
			FrmMainCustomer_Address dlg=new FrmMainCustomer_Address();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnselfinfo){
			FrmMainCustomer_Selfinfo dlg=new FrmMainCustomer_Selfinfo(this,"������Ϣ�޸�",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnaddcart){
			int i=FrmMainCustomer.this.dataTablePro.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				this.reloadProTable();
				curProduct=allProduct.get(i);
				product=curProduct;
			}
			int flag=0;
			for(int k=0;k<cartList.size();k++) {
				if(cartList.get(k).getPro_id()==curProduct.getPro_id()) {
					flag=1;
					BeanProduct temp=cartList.get(k);
					temp.setCount(temp.getCount()+1);
					cartList.remove(k);
					cartList.add(temp);
				}
			}
			if(flag==0) {
				cartList.add(curProduct);
			}
				
		}else if(e.getSource()==this.btnshopdiscount){
			int i=FrmMainCustomer.this.dataTableShop.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ���̼�", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curShop=allShop.get(i);
				shop=curShop;
			}
			FrmMainCustomer_ShopDiscount dlg=new FrmMainCustomer_ShopDiscount();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnorders){
			FrmMainCustomer_Orders dlg=new FrmMainCustomer_Orders();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnrecommend){
			FrmMainCustomer_RecommendOrders dlg=new FrmMainCustomer_RecommendOrders();
			dlg.setVisible(true);
		}
	}
	
}
