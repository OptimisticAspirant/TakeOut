package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanManager;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmLogin extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private JButton btnLogin = new JButton("��½");
	private JButton btnCancel = new JButton("�˳�");
	private JButton btnRegister = new JButton("ע��");
	ButtonGroup bg = new ButtonGroup();
	private JRadioButton btnManager = new JRadioButton("����Ա");
	private JRadioButton btnCustomer = new JRadioButton("��ͨ�û�");
	
	private JLabel labelUser = new JLabel("�û���");
	private JLabel labelPwd = new JLabel("���룺");
	private JTextField edtUserId = new JTextField(20);
	private JPasswordField edtPwd = new JPasswordField(20);
	public FrmMainCustomer frmMainCustomer= null;
	public FrmLogin(Frame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(this.btnRegister);
		toolBar.add(btnLogin);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelUser);
		workPane.add(edtUserId);
		workPane.add(labelPwd);
		workPane.add(edtPwd);
		bg.add(btnManager);
		bg.add(btnCustomer);
		workPane.add(btnManager);
		workPane.add(btnCustomer);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(280, 160);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		btnLogin.addActionListener(this);
		btnCancel.addActionListener(this);
		this.btnRegister.addActionListener(this);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.btnLogin && btnManager.isSelected()) {
			int userid=Integer.parseInt(this.edtUserId.getText());
			String pwd=new String(this.edtPwd.getPassword());
			try {
				BeanManager.currentLoginUser= TakeOutUtil.managerManager.login(userid, pwd);
				FrmMainManager dlg=new FrmMainManager();
				dlg.setVisible(true);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		} else if (e.getSource() == this.btnLogin && btnCustomer.isSelected()) {
			int userid=Integer.parseInt(this.edtUserId.getText());
			String pwd=new String(this.edtPwd.getPassword());
			try {
				BeanCustomer.currentLoginUser= TakeOutUtil.customerManager.login(userid, pwd);
				FrmMainCustomer dlg=new FrmMainCustomer();
				frmMainCustomer=dlg;
				dlg.setVisible(true);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			this.setVisible(false);
		} else if (e.getSource() == this.btnCancel) {
			System.exit(0);
		} else if(e.getSource()==this.btnLogin && !btnCustomer.isSelected()&& !btnManager.isSelected()){
			JOptionPane.showMessageDialog(null, "��ѡ���¼���", "����",JOptionPane.ERROR_MESSAGE);
			return;
		}else if(e.getSource()==this.btnRegister && btnManager.isSelected()){
			FrmManagerRegister dlg=new FrmManagerRegister(this,"����Աע��",true);
			dlg.setVisible(true);
		} else if(e.getSource()==this.btnRegister && btnCustomer.isSelected()){
			FrmCustomerRegister dlg=new FrmCustomerRegister(this,"�û�ע��",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnRegister && !btnCustomer.isSelected()&& !btnManager.isSelected()){
			JOptionPane.showMessageDialog(null, "��ѡ��ע�����", "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
}
