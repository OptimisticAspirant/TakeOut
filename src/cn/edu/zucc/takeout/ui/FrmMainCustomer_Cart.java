package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer_Cart extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnmodify=new JButton("修改数量");
    private JButton btnsettlement=new JButton("结算");
    private JButton btndelete=new JButton("删除");
    
    private Object tblShopTitle[]=BeanShopkeeper.tableTitles;
	private Object tblShopData[][];
	DefaultTableModel tabShopModel=new DefaultTableModel();
	private JTable dataTableShop=new JTable(tabShopModel);
    
	private String tblCartTitle[]=new String[]{"商品编号","商品分类","商品名称","商品价格","优惠价格","商品数量"};
	
	private Object tblCartData[][];
	DefaultTableModel tabCartModel=new DefaultTableModel();
	private JTable dataTableCart=new JTable(tabCartModel);
	
	private BeanShopkeeper curShop=null;
	List<BeanShopkeeper> allShop=null;
	private BeanProduct curCart=null;
	List<BeanProduct> allCarts=null;
	
	public static BeanShopkeeper cartshop=null;
	
	public void reloadShopTable(){
		try {
			allShop=TakeOutUtil.shopkeeperManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
	
	public void reloadCartTable(BeanShopkeeper shop){
		allCarts=FrmMainCustomer.cartList;
		tblCartData =  new Object[allCarts.size()][6];
		for(int i=0;i<allCarts.size();i++){
			
			if(allCarts.get(i).getShop_id()==shop.getShop_id()) {
				for(int j=0;j<6;j++)
					tblCartData[i][j]=allCarts.get(i).getCell(j);
			}
			
		}
		tabCartModel.setDataVector(tblCartData,tblCartTitle);
		this.dataTableCart.validate();
		this.dataTableCart.repaint();
	}
	
	public void reloadCartOnlyTable(){
		allCarts=FrmMainCustomer.cartList;
		tblCartData =  new Object[allCarts.size()][6];
		for(int i=0;i<allCarts.size();i++){
			if(allCarts.get(i).getShop_id()==curShop.getShop_id()) {
				for(int j=0;j<6;j++)
					tblCartData[i][j]=allCarts.get(i).getCell(j);
			}
			
		}
		tabCartModel.setDataVector(tblCartData,tblCartTitle);
		this.dataTableCart.validate();
		this.dataTableCart.repaint();
	}
	
	
    public FrmMainCustomer_Cart(){
    	
	    this.reloadShopTable();
    	
    	this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("购物车");
    	
    	this.btndelete.addActionListener(this);
	    this.btnsettlement.addActionListener(this);
	    this.btnmodify.addActionListener(this);
    	
	    toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
	    menubar.add(toolBar);
	    toolBar.add(btnmodify);
	    toolBar.add(btnsettlement);
	    toolBar.add(btndelete);
	    this.setJMenuBar(menubar);
	    this.getContentPane().add(toolBar,BorderLayout.SOUTH);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableShop), BorderLayout.WEST);
	    this.dataTableShop.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_Cart.this.dataTableShop.getSelectedRow();
				if(i<0) {
					return;
				}
				curShop=allShop.get(i);
				FrmMainCustomer_Cart.this.reloadCartTable(curShop);
			}
	    	
	    });
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableCart), BorderLayout.CENTER);
	    this.dataTableCart.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_Cart.this.dataTableCart.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btndelete){
			int i=FrmMainCustomer_Cart.this.dataTableCart.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				if(FrmMainCustomer.cartList.get(i).getCount()>1) {
					FrmMainCustomer.cartList.get(i).setCount(FrmMainCustomer.cartList.get(i).getCount()-1);
				}else {
					FrmMainCustomer.cartList.remove(allCarts.get(i));
				}
				reloadCartOnlyTable();
			}
		}else if(e.getSource()==this.btnsettlement){
			int i=FrmMainCustomer_Cart.this.dataTableShop.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商家", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curShop=allShop.get(i);
				cartshop=curShop;
			}
			FrmCartSettle dlg=new FrmCartSettle(this, "结算", true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnmodify){
			int i=FrmMainCustomer_Cart.this.dataTableCart.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curCart=allCarts.get(i);
				String title = JOptionPane.showInputDialog(null, "请输入想要修改的商品数量：\n", "数量修改", JOptionPane.PLAIN_MESSAGE);
		        int productcount=Integer.parseInt(title);
		        FrmMainCustomer.cartList.remove(curCart);
		        curCart.setCount(productcount);
		        FrmMainCustomer.cartList.add(curCart);
				reloadCartOnlyTable();
			}
		}
	}
	
}
