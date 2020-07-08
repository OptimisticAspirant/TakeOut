package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
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
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanAddress;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmAddressAdd extends JDialog implements ActionListener{
	
	public BeanAddress address=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelProvince = new JLabel("省份：");
	private JLabel labelCity = new JLabel("城市：");
	private JLabel labelArea = new JLabel("地区：");
	private JLabel labelLocation = new JLabel("地址：");
	private JLabel labelContacts = new JLabel("姓名：");
	private JLabel labelPhonenumber = new JLabel("电话：");
	
	private JTextField edtProvince = new JTextField(20);
	private JTextField edtCity = new JTextField(20);
	private JTextField edtArea = new JTextField(20);
	private JTextField edtLocation = new JTextField(20);
	private JTextField edtContacts = new JTextField(20);
	private JTextField edtPhonenumber = new JTextField(20);
	
	public FrmAddressAdd(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelProvince);
		workPane.add(edtProvince);
		workPane.add(labelCity);
		workPane.add(edtCity);
		workPane.add(labelArea);
		workPane.add(edtArea);
		workPane.add(labelLocation);
		workPane.add(edtLocation);
		workPane.add(labelContacts);
		workPane.add(edtContacts);
		workPane.add(labelPhonenumber);
		workPane.add(edtPhonenumber);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 240);
		
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
			String province=this.edtProvince.getText();
			String city=this.edtCity.getText();
			String area=this.edtArea.getText();
			String location=this.edtLocation.getText();
			String contacts=this.edtContacts.getText();
			String phonenumber=this.edtPhonenumber.getText();
			try {
				TakeOutUtil.addressManager.addaddress(BeanCustomer.currentLoginUser,province,city,area,location,contacts,phonenumber);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
}
