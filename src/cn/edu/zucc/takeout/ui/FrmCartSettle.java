package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.control.AddressManager;
import cn.edu.zucc.takeout.control.CartManager;
import cn.edu.zucc.takeout.control.CouponManager;
import cn.edu.zucc.takeout.control.ProductManager;
import cn.edu.zucc.takeout.model.BeanAddress;
import cn.edu.zucc.takeout.model.BeanCouponhold;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmCartSettle extends JDialog implements ActionListener{
	
	public static int key=0;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelSelectCoupon = new JLabel("选择优惠券：");
	private JComboBox edtSelectCoupon=new JComboBox();
	private JLabel labelSelectAddress = new JLabel("选择配送地址：");
	private JComboBox edtSelectAddress=new JComboBox();
	private JLabel labelSelectTime = new JLabel("输入送达时间(几分钟内)：");
	private JTextField edtSelectTime = new JTextField(10);
	
	public FrmCartSettle(JFrame f, String s, boolean b) {
		
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		workPane.add(labelSelectCoupon);
		workPane.add(edtSelectCoupon);
		edtSelectCoupon.addItem("----请选择可用优惠券----");
		List<BeanCouponhold> coupList=null;
		try {
			coupList = new CouponManager().loadCouponhold(BeanCustomer.currentLoginUser);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		for(int i=0;i<coupList.size();i++) {
			edtSelectCoupon.addItem(coupList.get(i).getSubtract());
		}
		
		workPane.add(labelSelectAddress);
		workPane.add(edtSelectAddress);
		edtSelectAddress.addItem("------请选择配送地址-----");
		List<BeanAddress> addList=null;
		try {
			addList = new AddressManager().loadCusAddresses(BeanCustomer.currentLoginUser);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		for(int i=0;i<addList.size();i++) {
			edtSelectAddress.addItem(addList.get(i).getLocation());
		}
		
		workPane.add(labelSelectTime);
		workPane.add(edtSelectTime);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 200);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			
			BeanShopkeeper shop=null;
			shop=new FrmMainManager_Shopkeeper().shop;
			
			int selectedCouponId=this.edtSelectCoupon.getSelectedIndex();
			List<BeanCouponhold> coupList=null;
			try {
				coupList=new CouponManager().loadCouponhold(BeanCustomer.currentLoginUser);
			} catch (BaseException e3) {
				e3.printStackTrace();
			}
			int coupid=coupList.get(selectedCouponId).getCoup_id();
			
			int selectedAddressId=this.edtSelectAddress.getSelectedIndex();
			List<BeanAddress> addList=null;
			try {
				addList = new AddressManager().loadCusAddresses(BeanCustomer.currentLoginUser);
			} catch (BaseException e2) {
				e2.printStackTrace();
			};
			int addressid=addList.get(selectedAddressId).getAdd_id();
			
			int minute=Integer.parseInt(this.edtSelectTime.getText());
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			String time = df.format(System.currentTimeMillis()+minute*60000);
			Timestamp requiretime = Timestamp.valueOf(time);
			
			float originprice=0;
			for(int i=0;i<FrmMainCustomer.cartList.size();i++) {
				originprice=originprice+FrmMainCustomer.cartList.get(i).getPro_price();
			}
					
			float finalprice=0;
			for(int i=0;i<FrmMainCustomer.cartList.size();i++) {
				finalprice=finalprice+FrmMainCustomer.cartList.get(i).getPro_discount();
			}
			try {
				finalprice=finalprice-(new CartManager().settle(coupid));
			} catch (BaseException e2) {
				e2.printStackTrace();
			}
			
			try {
				key=TakeOutUtil.CartManager.settle(shop, BeanCustomer.currentLoginUser, coupid, addressid, originprice, finalprice, requiretime);;
				FrmCartFinal dlg=new FrmCartFinal();
				dlg.setVisible(true);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
			
		}
		
	}
	
}
