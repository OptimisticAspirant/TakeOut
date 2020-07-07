package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
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
import javax.swing.table.DefaultTableModel;

import com.mchange.v2.codegen.bean.BeangenUtils;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanRider;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainManager_User extends JFrame implements ActionListener{
private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnuserdelete=new JButton("删除用户");
    
    private Object tblUserTitle[]=BeanCustomer.tableTitles;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel=new DefaultTableModel();
	private JTable dataTableUser=new JTable(tabUserModel);
	
	private BeanCustomer curUser=null;
	List<BeanCustomer> allUser=null;
	
	private void reloadUserTable(){
		try {
			allUser=TakeOutUtil.customerManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblUserData =  new Object[allUser.size()][BeanCustomer.tableTitles.length];
		for(int i=0;i<allUser.size();i++){
			for(int j=0;j<BeanCustomer.tableTitles.length;j++)
				tblUserData[i][j]=allUser.get(i).getCell(j);
		}
		tabUserModel.setDataVector(tblUserData,tblUserTitle);
		this.dataTableUser.validate();
		this.dataTableUser.repaint();
	}
	
	public FrmMainManager_User() {
		
		this.setTitle("客户管理");
    	this.setExtendedState(Frame.MAXIMIZED_BOTH);
		
		this.btnuserdelete.addActionListener(this);
		
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnuserdelete);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableUser), BorderLayout.CENTER);
	    this.dataTableUser.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainManager_User.this.dataTableUser.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    this.reloadUserTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==this.btnuserdelete){
			int i=FrmMainManager_User.this.dataTableUser.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择骑手", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curUser=allUser.get(i);
			}
			try {
				TakeOutUtil.customerManager.deleteuser(this.curUser);
				this.reloadUserTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}

}
