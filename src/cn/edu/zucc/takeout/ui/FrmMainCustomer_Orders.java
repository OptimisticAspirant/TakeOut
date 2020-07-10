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

    private JButton btnEvaluateProduct=new JButton("������Ʒ");
    private JButton btnEvaluateRider=new JButton("��������");
    
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
	
	private void reloadOrdTable(){
		try {
			allOrder=TakeOutUtil.orderManager.loadOrders(BeanCustomer.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
	
	private void reloadOrdDetialTable(int orderIdx){
		if(orderIdx<0) return;
		curOrder=allOrder.get(orderIdx);
		try {
			allDetail=TakeOutUtil.orderManager.loadDetails(curOrder);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
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
	
	
	public FrmMainCustomer_Orders() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("���ж���");
		
		reloadOrdTable();
    
	    this.btnEvaluateRider.addActionListener(this);
	    this.btnEvaluateProduct.addActionListener(this);
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnEvaluateRider);
	    toolBar.add(btnEvaluateProduct);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableOrd), BorderLayout.WEST);
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
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableDetail), BorderLayout.CENTER);
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
				JOptionPane.showMessageDialog(null, "��ѡ�񶩵�", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
//				if(FrmMainCustomer.cartList.get(i).getCount()>1) {
//					FrmMainCustomer.cartList.get(i).setCount(FrmMainCustomer.cartList.get(i).getCount()-1);
//				}else {
//					FrmMainCustomer.cartList.remove(allCarts.get(i));
//				}
//				reloadCartOnlyTable();
			}
		}else if(e.getSource()==this.btnEvaluateProduct){
			int i=FrmMainCustomer_Orders.this.dataTableDetail.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
//				curShop=allShop.get(i);
//				cartshop=curShop;
			}
//			FrmCartSettle dlg=new FrmCartSettle(this, "����", true);
//			dlg.setVisible(true);
		}
	}
	
}
