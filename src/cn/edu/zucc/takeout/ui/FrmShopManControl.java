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

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.model.BeanDiscount;
import cn.edu.zucc.takeout.model.BeanPreferential;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmShopManControl extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnmanadd=new JButton("�����������");
    private JButton btnmandelete=new JButton("ɾ����������");

	private Object tblManTitle[]=BeanPreferential.tableTitles;
	private Object tblManData[][];
	DefaultTableModel tabManModel=new DefaultTableModel();
	private JTable dataTableMan=new JTable(tabManModel);
	
	private BeanPreferential curMan=null;
	List<BeanPreferential> allMan=null;

	private void reloadManTable(){
		try {
			allMan=TakeOutUtil.couponManager.loadShopMan(FrmMainManager_Shopkeeper.shop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblManData =  new Object[allMan.size()][BeanDiscount.tableTitles.length];
		for(int i=0;i<allMan.size();i++){
			for(int j=0;j<BeanPreferential.tableTitles.length;j++)
				tblManData[i][j]=allMan.get(i).getCell(j);
		}
		tabManModel.setDataVector(tblManData,tblManTitle);
		this.dataTableMan.validate();
		this.dataTableMan.repaint();
	}
	
	public FrmShopManControl() {
		
		reloadManTable();
		
		this.setTitle("�̼�������������");
		
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		
	    this.btnmanadd.addActionListener(this);
	    this.btnmandelete.addActionListener(this);
	    
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnmanadd);
	    toolBar.add(btnmandelete);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableMan), BorderLayout.CENTER);

	    this.dataTableMan.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmShopManControl.this.dataTableMan.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnmanadd){
			FrmManAdd dlg=new FrmManAdd(this,"�����������",true);
			dlg.setVisible(true);
		    this.reloadManTable();
		}else if(e.getSource()==this.btnmandelete){
			int i=FrmShopManControl.this.dataTableMan.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����������", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
			    this.reloadManTable();
				curMan=allMan.get(i);
			}
			try {
				TakeOutUtil.couponManager.deletepreferential(this.curMan);
			    this.reloadManTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
}
