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

    private JButton btncart=new JButton("购物车");
    private JButton btnselfinfo=new JButton("个人信息");
    private JButton btnaddress=new JButton("地址管理");
    private JButton btncoupon=new JButton("优惠券管理");
    private JButton btnorders=new JButton("所有订单");
    private JButton btnmodifyPwd=new JButton("密码修改");
    private JButton btnregister=new JButton("账户注册");

	private JPanel statusBar = new JPanel();
	
	public FrmMainCustomer() {

		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖助手");
    
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
	    
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!"+BeanCustomer.currentLoginUser.getCust_name()+"!");
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
			FrmCustomerRegister dlg=new FrmCustomerRegister(this,"账号注册",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnmodifyPwd){
			FrmCustomerModifyPwd dlg=new FrmCustomerModifyPwd(this,"密码修改",true);
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
