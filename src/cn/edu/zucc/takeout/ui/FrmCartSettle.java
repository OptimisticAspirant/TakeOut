package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmCartSettle extends JDialog implements ActionListener{
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelSelectCoupon = new JLabel("选择优惠券：");
	private JComboBox edtSelectCoupon=new JComboBox();
	private JLabel labelSelectAddress = new JLabel("选择地址：");
	private JComboBox edtSelectAddress=new JComboBox();
	private JLabel labelSelectTime = new JLabel("选择送达时间：");
	private JTextField edtSelectTime = new JTextField(15);
	
	public FrmCartSettle(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelSelectCoupon);
		workPane.add(edtSelectCoupon);
		workPane.add(labelSelectAddress);
		workPane.add(edtSelectAddress);
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
//			BeanShopkeeper shop=null;
//			shop=new FrmMainManager_Shopkeeper().shop;
//			float amount=Float.parseFloat(this.edtAmount.getText());
//			int count=Integer.parseInt(this.edtDiscount.getText());
//			String start=this.edtStart.getText();
//			String end=this.edtEnd.getText();
//			try {
//				TakeOutUtil.couponManager.addcou(shop, amount, count, start, end);
//				FrmAddressAdd dlg=new FrmAddressAdd(this,"添加地址",true);
//				dlg.setVisible(true);
//				this.setVisible(false);
//			} catch (BaseException e1) {
//				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
//				return;
//			}
		}
		
	}
	
}
