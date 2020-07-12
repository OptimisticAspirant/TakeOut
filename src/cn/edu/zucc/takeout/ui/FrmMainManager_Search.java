package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.model.BeanRiderbill;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainManager_Search extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();
	
	private JTextField edttext = new JTextField(20);
	private JButton btnsearchCustomer=new JButton("ËÑË÷ÓÃ»§");
	private JButton btnsearchRider=new JButton("ËÑË÷ÆïÊÖ");
	private JButton btnsearchProduct=new JButton("ËÑË÷ÉÌÆ·");
	
	private BeanCustomer curCustomer=null;
	List<BeanCustomer> allCustomer=null;
	private BeanRiderbill curRiderbill=null;
	List<BeanRiderbill> allriRiderbills=null;
	private BeanProduct curPro=null;
	List<BeanProduct> allPro=null;
	
	private Object tblProTitle[]=BeanProduct.tableTitles;
	private Object tblProData[][];
	DefaultTableModel tabProModel=new DefaultTableModel();
	private JTable dataTablePro=new JTable(tabProModel);
	
	private Object tblRiderTitle[]=BeanRiderbill.tableTitles;
	private Object tblRiderData[][];
	DefaultTableModel tabRiderModel=new DefaultTableModel();
	private JTable dataTableRider=new JTable(tabRiderModel);
	
	private Object tblCustTitle[]=BeanCustomer.tableTitles;
	private Object tblCustData[][];
	DefaultTableModel tabCustModel=new DefaultTableModel();
	private JTable dataTableCust=new JTable(tabCustModel);
	
	public FrmMainManager_Search(){

		this.setTitle("È«¾ÖËÑË÷");
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		
    
	    this.btnsearchCustomer.addActionListener(this);
	    this.btnsearchRider.addActionListener(this);
	    this.btnsearchProduct.addActionListener(this);
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(edttext);
	    toolBar.add(btnsearchCustomer);
	    toolBar.add(btnsearchRider);
	    toolBar.add(btnsearchProduct);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableCust), BorderLayout.WEST);
	    this.getContentPane().add(new JScrollPane(this.dataTableRider), BorderLayout.CENTER);
	    this.getContentPane().add(new JScrollPane(this.dataTablePro), BorderLayout.EAST);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnsearchCustomer){
			int custid=Integer.parseInt(edttext.getText());
			try {
				allCustomer=TakeOutUtil.searchManager.loadCust(custid);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			tblCustData =  new Object[allCustomer.size()][BeanCustomer.tableTitles.length];
			for(int i=0;i<allCustomer.size();i++){
				for(int j=0;j<BeanCustomer.tableTitles.length;j++)
					tblCustData[i][j]=allCustomer.get(i).getCell(j);
			}
			tabCustModel.setDataVector(tblCustData,tblCustTitle);
			this.dataTableCust.validate();
			this.dataTableCust.repaint();
		}else if(e.getSource()==this.btnsearchRider){
			int riderid=Integer.parseInt(edttext.getText());
			try {
				allriRiderbills=TakeOutUtil.searchManager.loadRiderbill(riderid);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			tblRiderData =  new Object[allriRiderbills.size()][BeanRiderbill.tableTitles.length];
			for(int i=0;i<allriRiderbills.size();i++){
				for(int j=0;j<BeanRiderbill.tableTitles.length;j++)
					tblRiderData[i][j]=allriRiderbills.get(i).getCell(j);
			}
			tabRiderModel.setDataVector(tblRiderData,tblRiderTitle);
			this.dataTableRider.validate();
			this.dataTableRider.repaint();
		}else if(e.getSource()==this.btnsearchProduct){
			int proid=Integer.parseInt(edttext.getText());
			try {
				allPro=TakeOutUtil.searchManager.loadPro(proid);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
				return;
			}
			tblProData =  new Object[allPro.size()][BeanProduct.tableTitles.length];
			for(int i=0;i<allPro.size();i++){
				for(int j=0;j<BeanProduct.tableTitles.length;j++)
					tblProData[i][j]=allPro.get(i).getCell(j);
			}
			tabProModel.setDataVector(tblProData,tblProTitle);
			this.dataTablePro.validate();
			this.dataTablePro.repaint();
		}
	}
	
}
