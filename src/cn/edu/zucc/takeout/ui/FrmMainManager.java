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

import cn.edu.zucc.takeout.model.BeanManager;

public class FrmMainManager extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnshop=new JButton("商家管理");
    private JButton btnrider=new JButton("骑手管理");
    private JButton btnuser=new JButton("用户管理");
    private JButton btnpro=new JButton("商品分类");
    private JButton btnmodifyPwd=new JButton("密码修改");
    private JButton btnregister=new JButton("账户注册");
    private JButton btnsearch=new JButton("全局搜索");

	private JPanel statusBar = new JPanel();

	public FrmMainManager(){
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("外卖助手");
    
	    this.btnregister.addActionListener(this);
	    this.btnmodifyPwd.addActionListener(this);
	    this.btnrider.addActionListener(this);
	    this.btnshop.addActionListener(this);
	    this.btnpro.addActionListener(this);
	    this.btnuser.addActionListener(this);
	    this.btnsearch.addActionListener(this);
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnrider);
	    toolBar.add(btnshop);
	    toolBar.add(btnuser);
	    toolBar.add(btnpro);
	    toolBar.add(btnmodifyPwd);
	    toolBar.add(btnregister);
	    toolBar.add(btnsearch);
	    this.setJMenuBar(menubar);
	    
	    //状态栏
	    statusBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    JLabel label=new JLabel("您好!"+BeanManager.currentLoginUser.getManager_name()+"!");
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
			FrmManagerRegister dlg=new FrmManagerRegister(this,"管理员注册",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnmodifyPwd){
			FrmManagerModifyPwd dlg=new FrmManagerModifyPwd(this,"密码修改",true);
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnshop){
			FrmMainManager_Shopkeeper dlg=new FrmMainManager_Shopkeeper();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnrider){
			FrmMainManager_Rider dlg=new FrmMainManager_Rider();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnpro){
			FrmMainManager_Category dlg=new FrmMainManager_Category();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnuser){
			FrmMainManager_User dlg=new FrmMainManager_User();
			dlg.setVisible(true);
		}else if(e.getSource()==this.btnsearch){
			FrmMainManager_Search dlg=new FrmMainManager_Search();
			dlg.setVisible(true);
		}
	}
}
