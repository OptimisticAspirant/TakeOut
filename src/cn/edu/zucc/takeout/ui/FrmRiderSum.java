package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.edu.zucc.takeout.control.RiderManager;


public class FrmRiderSum extends JDialog{
private JPanel statusBar=new JPanel();
	
	private static final long serialVersionUID = 1L;
	
	public FrmRiderSum(){
		
		this.setTitle("总计");
		
		this.setSize(230, 70);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
		statusBar.setLayout(new FlowLayout(FlowLayout.CENTER));
	    JLabel label=null;
		label = new JLabel("共接单数："+new RiderManager().riderSumCount(FrmMainManager_Rider.rider)+"   "+"累计收入："+new RiderManager().riderSumIncome(FrmMainManager_Rider.rider));
	    statusBar.add(label);
	    this.getContentPane().add(statusBar,BorderLayout.CENTER);
	    
	}
}
