package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Frame;
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
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanAddress;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.util.BaseException;


public class FrmMainCustomer_Address extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnadd=new JButton("添加地址");
//    private JButton btnmodify=new JButton("修改地址");
    private JButton btndelete=new JButton("删除地址");
    
	private Object tblAddressTitle[]=BeanAddress.tableTitles;
	private Object tblAddressData[][];
	DefaultTableModel tabAddressModel=new DefaultTableModel();
	private JTable dataTableAddress=new JTable(tabAddressModel);
	
	List<BeanAddress> allAddresses=null;
	private BeanAddress curAddress=null;
	
	private void reloadAddressTable(){
		try {
			allAddresses=TakeOutUtil.addressManager.loadCusAddresses(BeanCustomer.currentLoginUser);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblAddressData =  new Object[allAddresses.size()][BeanAddress.tableTitles.length];
		for(int i=0;i<allAddresses.size();i++){
			for(int j=0;j<BeanAddress.tableTitles.length;j++)
				tblAddressData[i][j]=allAddresses.get(i).getCell(j);
		}
		tabAddressModel.setDataVector(tblAddressData,tblAddressTitle);
		this.dataTableAddress.validate();
		this.dataTableAddress.repaint();
	}
	
	public FrmMainCustomer_Address() {
		
		this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("地址管理");
		
		this.btnadd.addActionListener(this);
	    this.btndelete.addActionListener(this);
	    
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnadd);
	    toolBar.add(btndelete);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableAddress), BorderLayout.CENTER);
	    this.dataTableAddress.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_Address.this.dataTableAddress.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    this.reloadAddressTable();
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnadd) {
			FrmAddressAdd dlg=new FrmAddressAdd(this,"添加地址",true);
			dlg.setVisible(true);
			reloadAddressTable();
		}else if(e.getSource()==this.btndelete){
			int i=FrmMainCustomer_Address.this.dataTableAddress.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择地址", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curAddress=allAddresses.get(i);
			}
			try {
				TakeOutUtil.addressManager.deleteaddress(curAddress);;
				this.reloadAddressTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}
	
}
