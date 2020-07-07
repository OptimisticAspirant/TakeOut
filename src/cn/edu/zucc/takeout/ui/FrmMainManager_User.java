package cn.edu.zucc.takeout.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanRider;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainManager_User extends JFrame implements ActionListener{
private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnruserdelete=new JButton("É¾³ýÓÃ»§");
    
    private Object tblUserTitle[]=BeanCustomer.tableTitles;
	private Object tblUserData[][];
	DefaultTableModel tabUserModel=new DefaultTableModel();
	private JTable dataTableUser=new JTable(tabUserModel);
	
	private BeanRider curUser=null;
	List<BeanRider> allUser=null;
	
//	private void reloadUserTable(){
//		try {
//			allUser=TakeOutUtil.customerManager.loadAll();
//		} catch (BaseException e) {
//			JOptionPane.showMessageDialog(null, e.getMessage(), "´íÎó",JOptionPane.ERROR_MESSAGE);
//			return;
//		}
//		tblRiderData =  new Object[allRider.size()][BeanRider.tableTitles.length];
//		for(int i=0;i<allRider.size();i++){
//			for(int j=0;j<BeanRider.tableTitles.length;j++)
//				tblRiderData[i][j]=allRider.get(i).getCell(j);
//		}
//		tabRiderModel.setDataVector(tblRiderData,tblRiderTitle);
//		this.dataTableRider.validate();
//		this.dataTableRider.repaint();
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}
