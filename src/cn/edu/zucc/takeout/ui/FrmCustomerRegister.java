package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmCustomerRegister extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("注册");
	private Button btnCancel = new Button("取消");
	
	private JLabel labelUser = new JLabel("用户编号：");
	private JLabel labelName = new JLabel("用户名称：");
	private JLabel labelGender = new JLabel("用户性别：");
	private JLabel labelPhone = new JLabel("常用手机：");
	private JLabel labelMail = new JLabel("常用邮箱：");
	private JLabel labelCity = new JLabel("所在城市：");
	private JLabel labelPwd = new JLabel("输入密码：");
	private JLabel labelPwd2 = new JLabel("确认密码：");
	private JTextField edtUserId = new JTextField(20);
	private JTextField edtUserName = new JTextField(20);
	private JTextField edtUserGender = new JTextField(20);
	private JTextField edtUserPhone = new JTextField(20);
	private JTextField edtUserMail = new JTextField(20);
	private JTextField edtUserCity = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	private JPasswordField edtPwd2 = new JPasswordField(20);
	public FrmCustomerRegister(Dialog f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
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
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 300);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		this.btnCancel.addActionListener(this);
		this.btnOk.addActionListener(this);
	}
	public FrmCustomerRegister(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
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
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		workPane.add(labelPwd2);
		workPane.add(edtPwd2);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 300);
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
			int userid=Integer.parseInt(this.edtUserId.getText());
			String username=this.edtUserName.getText();
			String gender=this.edtUserGender.getText();
			String phonenumber=this.edtUserPhone.getText();
			String mail=this.edtUserMail.getText();
			String city=this.edtUserCity.getText();
			String pwd1=new String(this.edtPwd.getPassword());
			String pwd2=new String(this.edtPwd2.getPassword());
			try {
				BeanCustomer user=TakeOutUtil.customerManager.reg(userid,username,gender,phonenumber,mail,city,pwd1,pwd2);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(),"错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
}