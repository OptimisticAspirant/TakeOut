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
import cn.edu.zucc.takeout.model.BeanCouponhold;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanDiscount;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer_Coupon extends JFrame implements ActionListener{

private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btncoupview=new JButton("查看优惠券");
    private JButton btncollectview=new JButton("查看集单数");
    private JButton btncoupget=new JButton("兑换优惠券");
    
	private Object tblCoupTitle[]=BeanCouponhold.tableTitles;
	private Object tblCoupData[][];
	DefaultTableModel tabCoupModel=new DefaultTableModel();
	private JTable dataTableCoup=new JTable(tabCoupModel);
	
	private Object tblCollectTitle[]=BeanDiscount.tableTitles;
	private Object tblCollectData[][];
	DefaultTableModel tabCollectModel=new DefaultTableModel();
	private JTable dataTableCollect=new JTable(tabCollectModel);

	private BeanCouponhold curCoupon=null;
	List<BeanCouponhold> allCoupons=null;
	private BeanDiscount curCollect=null;
	List<BeanDiscount> allCollect=null;
	
	private void reloadCoupTable(){
		try {
			allCoupons=TakeOutUtil.couponManager.loadCouponhold(BeanCustomer.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCoupData =  new Object[allCoupons.size()][BeanCouponhold.tableTitles.length];
		for(int i=0;i<allCoupons.size();i++){
			for(int j=0;j<BeanCouponhold.tableTitles.length;j++)
				tblCoupData[i][j]=allCoupons.get(i).getCell(j);
		}
		tabCoupModel.setDataVector(tblCoupData,tblCoupTitle);
		this.dataTableCoup.validate();
		this.dataTableCoup.repaint();
	}
	
	private void reloadCollectTable(){
		try {
			allCollect=TakeOutUtil.couponManager.loadCollect(BeanCustomer.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCollectData =  new Object[allCollect.size()][BeanDiscount.tableTitles.length];
		for(int i=0;i<allCollect.size();i++){
			for(int j=0;j<BeanDiscount.tableTitles.length;j++)
				tblCollectData[i][j]=allCollect.get(i).getCell(j);
		}
		tabCollectModel.setDataVector(tblCollectData,tblCollectTitle);
		this.dataTableCollect.validate();
		this.dataTableCollect.repaint();
	}
    
    public FrmMainCustomer_Coupon(){
    	
    	this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("优惠券管理");
    	
    	this.btncollectview.addActionListener(this);
	    this.btncoupget.addActionListener(this);
	    this.btncoupview.addActionListener(this);
    	
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btncoupview);
	    toolBar.add(btncollectview);
	    toolBar.add(btncoupget);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableCoup), BorderLayout.WEST);
	    this.dataTableCoup.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_Coupon.this.dataTableCoup.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    this.reloadCoupTable();
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableCollect), BorderLayout.CENTER);
	    this.dataTableCollect.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_Coupon.this.dataTableCollect.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    this.reloadCollectTable();
	    
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btncoupget){
			int i=FrmMainCustomer_Coupon.this.dataTableCollect.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择集单信息", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curCollect=allCollect.get(i);
			}
			try {
				TakeOutUtil.couponManager.changecoupon(curCollect);
				this.reloadCollectTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	
}
