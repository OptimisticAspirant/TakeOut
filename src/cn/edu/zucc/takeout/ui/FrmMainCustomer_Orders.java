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
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanOrderdetail;
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer_Orders extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	private JPanel toolBar=new JPanel();

    private JButton btnEvaluateProduct=new JButton("评价商品");
    private JButton btnEvaluateRider=new JButton("评价骑手");
    private JButton btndelete=new JButton("删除订单");
    private JButton btncancel=new JButton("取消订单");
    private JButton btnreceive=new JButton("确认送达");
    
	private Object tblOrdTitle[]=BeanProductorder.tableTitles;
	private Object tblOrdData[][];
	DefaultTableModel tabOrdModel=new DefaultTableModel();
	private JTable dataTableOrd=new JTable(tabOrdModel);
	
	private Object tblDetailTitle[]=BeanOrderdetail.tableTitles;
	private Object tblDetailData[][];
	DefaultTableModel tabDetailModel=new DefaultTableModel();
	private JTable dataTableDetail=new JTable(tabDetailModel);
	
	private BeanProductorder curOrder=null;
	List<BeanProductorder> allOrder=null;
	private BeanOrderdetail curDetail=null;
	List<BeanOrderdetail> allDetail=null;
	
	public static BeanProductorder order=null;
	public static BeanOrderdetail detail=null;
	
	private void reloadOrdTable(){
		try {
			allOrder=TakeOutUtil.orderManager.loadOrders(BeanCustomer.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrdData = new Object[allOrder.size()][BeanProductorder.tableTitles.length];
		for(int i=0;i<allOrder.size();i++){
			for(int j=0;j<BeanProductorder.tableTitles.length;j++)
				tblOrdData[i][j]=allOrder.get(i).getCell(j);
		}
		tabOrdModel.setDataVector(tblOrdData,tblOrdTitle);
		this.dataTableOrd.validate();
		this.dataTableOrd.repaint();
	}
	
	private void reloadDetialTable(){
		try {
			allDetail=TakeOutUtil.orderManager.loadDetails(curOrder);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblDetailData = new Object[allDetail.size()][BeanOrderdetail.tableTitles.length];
		for(int i=0;i<allDetail.size();i++){
			for(int j=0;j<BeanOrderdetail.tableTitles.length;j++)
				tblDetailData[i][j]=allDetail.get(i).getCell(j);
		}
		tabDetailModel.setDataVector(tblDetailData,tblDetailTitle);
		this.dataTableDetail.validate();
		this.dataTableDetail.repaint();
	}
	
	private void reloadOrdDetialTable(int orderIdx){
		if(orderIdx<0) return;
		curOrder=allOrder.get(orderIdx);
		try {
			allDetail=TakeOutUtil.orderManager.loadDetails(curOrder);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblDetailData = new Object[allDetail.size()][BeanOrderdetail.tableTitles.length];
		for(int i=0;i<allDetail.size();i++){
			for(int j=0;j<BeanOrderdetail.tableTitles.length;j++)
				tblDetailData[i][j]=allDetail.get(i).getCell(j);
		}
		tabDetailModel.setDataVector(tblDetailData,tblDetailTitle);
		this.dataTableDetail.validate();
		this.dataTableDetail.repaint();
	}
	
	
	public FrmMainCustomer_Orders() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("所有订单");
		
		reloadOrdTable();
    
	    this.btnEvaluateRider.addActionListener(this);
	    this.btnEvaluateProduct.addActionListener(this);
	    this.btnreceive.addActionListener(this);
	    this.btncancel.addActionListener(this);
	    this.btndelete.addActionListener(this);
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnreceive);
	    toolBar.add(btnEvaluateRider);
	    toolBar.add(btnEvaluateProduct);
	    toolBar.add(btncancel);
	    toolBar.add(btndelete);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableOrd), BorderLayout.CENTER);
	    this.dataTableOrd.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_Orders.this.dataTableOrd.getSelectedRow();
				if(i<0) {
					return;
				}
				curOrder=allOrder.get(i);
				FrmMainCustomer_Orders.this.reloadOrdDetialTable(i);
			}
	    	
	    });
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableDetail), BorderLayout.EAST);
	    this.dataTableDetail.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_Orders.this.dataTableDetail.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnEvaluateRider){
			int i=FrmMainCustomer_Orders.this.dataTableOrd.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curOrder=allOrder.get(i);
				order=curOrder;
				FrmMainCustomer_RiderEvaluate dlg=new FrmMainCustomer_RiderEvaluate(this, "评价骑手", true);
				dlg.setVisible(true);
			}
		}else if(e.getSource()==this.btnEvaluateProduct){
			int i=FrmMainCustomer_Orders.this.dataTableDetail.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curDetail=allDetail.get(i);
				detail=curDetail;
			}
			FrmMainCustomer_ProductEvaluate dlg=new FrmMainCustomer_ProductEvaluate(this, "评价商品", true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btndelete){
			int i=FrmMainCustomer_Orders.this.dataTableOrd.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curOrder=allOrder.get(i);
			}
			try {
				TakeOutUtil.orderManager.deleteorders(curOrder);
				reloadOrdTable();
				reloadDetialTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==this.btnreceive){
			int i=FrmMainCustomer_Orders.this.dataTableOrd.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curOrder=allOrder.get(i);
			}
			try {
				TakeOutUtil.orderManager.receiveorders(curOrder);
				reloadOrdTable();
				reloadDetialTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}else if(e.getSource()==this.btncancel){
			int i=FrmMainCustomer_Orders.this.dataTableOrd.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curOrder=allOrder.get(i);
			}
			try {
				TakeOutUtil.orderManager.cancelorders(curOrder);
				reloadOrdTable();
				reloadDetialTable();
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
		}
	}
}
