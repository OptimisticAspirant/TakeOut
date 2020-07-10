package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.zucc.takeout.control.CartManager;
import cn.edu.zucc.takeout.control.CustomerManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmCartFinal extends JFrame{
	
	private JPanel statusBar=new JPanel();
	
	public FrmCartFinal() throws BaseException {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("结算");
		
		statusBar.setLayout(new FlowLayout(FlowLayout.CENTER));
	    JLabel label=null;
		label = new JLabel("原始金额："+new CartManager().searchorigin(FrmCartSettle.key)+"\n"+"结算金额："+new CartManager().searchfinal(FrmCartSettle.key)+"\n");
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.CENTER);
	    
	}
	

}
