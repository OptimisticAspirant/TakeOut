package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.control.CustomerManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer_Selfinfo extends JDialog implements ActionListener{
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelName = new JLabel("用户名称：");
	private JLabel labelGender = new JLabel("用户性别：");
	private JLabel labelPhone = new JLabel("常用手机：");
	private JLabel labelMail = new JLabel("常用邮箱：");
	private JLabel labelCity = new JLabel("所在城市：");
	private JTextField edtUserName = new JTextField(BeanCustomer.currentLoginUser.getCust_name(),20);
	private JTextField edtUserGender = new JTextField(BeanCustomer.currentLoginUser.getCust_gender(),20);
	private JTextField edtUserPhone = new JTextField(BeanCustomer.currentLoginUser.getCust_phone(),20);
	private JTextField edtUserMail = new JTextField(BeanCustomer.currentLoginUser.getCust_mail(),20);
	private JTextField edtUserCity = new JTextField(BeanCustomer.currentLoginUser.getCust_city(),20);
	public FrmMainCustomer_Selfinfo(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelName);
		workPane.add(edtUserName);
		workPane.add(labelGender);
		workPane.add(edtUserGender);
		workPane.add(labelPhone);
		workPane.add(edtUserPhone);
		workPane.add(labelMail);
		workPane.add(edtUserMail);
		workPane.add(labelCity);
		workPane.add(edtUserCity);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 220);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.btnCancel)
			this.setVisible(false);
		else if(e.getSource()==this.btnOk){
			String username=this.edtUserName.getText();
			String gender=this.edtUserGender.getText();
			String phonenumber=this.edtUserPhone.getText();
			String mail=this.edtUserMail.getText();
			String city=this.edtUserCity.getText();
			try {
				TakeOutUtil.customerManager.modify(BeanCustomer.currentLoginUser,username,gender,phonenumber,mail,city);
				BeanCustomer.currentLoginUser.setCust_city(city);
				BeanCustomer.currentLoginUser.setCust_gender(gender);
				BeanCustomer.currentLoginUser.setCust_mail(mail);
				BeanCustomer.currentLoginUser.setCust_name(username);
				BeanCustomer.currentLoginUser.setCust_phone(phonenumber);
				FrmStart.dlgLogin.frmMainCustomer.label.setText("您好!  "+BeanCustomer.currentLoginUser.getCust_name()+"!  "+new CustomerManager().VipInfomation(BeanCustomer.currentLoginUser));
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}	
	
}
