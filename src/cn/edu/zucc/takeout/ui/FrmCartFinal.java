package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.zucc.takeout.control.CustomerManager;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmCartFinal extends JFrame{
	
	private JPanel statusBar=new JPanel();
	
	public FrmCartFinal() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("Ω·À„");
		
		statusBar.setLayout(new FlowLayout(FlowLayout.CENTER));
	    JLabel label=null;
//		try {
//			label = new JLabel("Ω·À„Ω∂Ó£∫"+);
//		} catch (BaseException e1) {
//			e1.printStackTrace();
//		}
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.CENTER);
	    
	}
	

}
