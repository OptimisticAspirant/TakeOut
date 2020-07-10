package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.zucc.takeout.control.CartManager;
import cn.edu.zucc.takeout.control.CustomerManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmCartFinal extends JFrame{
	
	private JPanel statusBar=new JPanel();
	
	private static final long serialVersionUID = 1L;
	
	public FrmCartFinal() throws BaseException {
		
		this.setTitle("结算");
		
		this.setSize(230, 70);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		statusBar.setLayout(new FlowLayout(FlowLayout.CENTER));
	    JLabel label=null;
		label = new JLabel("原始金额："+new CartManager().searchorigin(FrmCartSettle.key)+"   "+"结算金额："+new CartManager().searchfinal(FrmCartSettle.key));
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.CENTER);
	    
	}
	

}
