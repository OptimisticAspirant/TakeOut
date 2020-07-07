package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import cn.edu.zucc.takeout.model.BeanCustomer;

public class FrmMainCustomer extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btncart=new JButton("���ﳵ");
    private JButton btnselfinfo=new JButton("������Ϣ");
    private JButton btnaddress=new JButton("��ַ����");
    private JButton btncoupon=new JButton("�Ż�ȯ����");
    private JButton btnorders=new JButton("���ж���");
    private JButton btnmodifyPwd=new JButton("�����޸�");
    private JButton btnregister=new JButton("�˻�ע��");

	private JPanel statusBar = new JPanel();
	
	public FrmMainCustomer() {

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("��������");
    
	    this.btnselfinfo.addActionListener(this);
	    this.btnaddress.addActionListener(this);
	    this.btncoupon.addActionListener(this);
	    this.btnorders.addActionListener(this);
	    this.btnmodifyPwd.addActionListener(this);
	    this.btnregister.addActionListener(this);
	    this.btncart.addActionListener(this);
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btncart);
	    toolBar.add(btnselfinfo);
	    toolBar.add(btnaddress);
	    toolBar.add(btncoupon);
	    toolBar.add(btnorders);
	    toolBar.add(btnmodifyPwd);
	    toolBar.add(btnregister);
	    this.setJMenuBar(menubar);
	    
	    //״̬��
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("����!"+BeanCustomer.currentLoginUser.getCust_name()+"!");
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.SOUTH);
	    this.addWindowListener(new WindowAdapter(){   
	    	public void windowClosing(WindowEvent e){ 
	    		System.exit(0);
             }
        });
	    this.setVisible(true);
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnregister){
			FrmCustomerRegister dlg=new FrmCustomerRegister(this,"�˺�ע��",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnmodifyPwd){
			FrmCustomerModifyPwd dlg=new FrmCustomerModifyPwd(this,"�����޸�",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btncoupon){
			FrmMainManager_Rider dlg=new FrmMainManager_Rider();
			dlg.setVisible(true);
		}
//			else if(e.getSource()==this.btnpro){
//			FrmMainManager_Category dlg=new FrmMainManager_Category();
//			dlg.setVisible(true);
//		}else if(e.getSource()==this.btnuser){
//			FrmMainManager_User dlg=new FrmMainManager_User();
//			dlg.setVisible(true);
//		}
	}
	
}
