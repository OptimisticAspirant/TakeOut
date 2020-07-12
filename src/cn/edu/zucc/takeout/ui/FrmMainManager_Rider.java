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
import cn.edu.zucc.takeout.model.BeanRider;
import cn.edu.zucc.takeout.model.BeanRiderbill;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainManager_Rider extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnrideradd=new JButton("ÃÌº”∆Ô ÷");
    private JButton btnridermodify=new JButton("±‡º≠∆Ô ÷");
    private JButton btnriderdelete=new JButton("…æ≥˝∆Ô ÷");
    private JButton btnorder=new JButton("∑÷≈‰∂©µ•");
    
	private Object tblRiderTitle[]=BeanRider.tableTitles;
	private Object tblRiderData[][];
	DefaultTableModel tabRiderModel=new DefaultTableModel();
	private JTable dataTableRider=new JTable(tabRiderModel);
	
	private Object tblRiderbillTitle[]=BeanRiderbill.tableTitles;
	private Object tblRiderbillData[][];
	DefaultTableModel tabRiderbillModel=new DefaultTableModel();
	private JTable dataTableRiderbill=new JTable(tabRiderbillModel);

	private BeanRider curRider=null;
	List<BeanRider> allRider=null;
	public static BeanRider rider=null;
	private BeanRiderbill curRiderbill=null;
	List<BeanRiderbill> allRiderbills=null;
	public static BeanRiderbill riderbill=null;
	
	private void reloadRiderTable(){
		try {
			allRider=TakeOutUtil.riderManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRiderData =  new Object[allRider.size()][BeanRider.tableTitles.length];
		for(int i=0;i<allRider.size();i++){
			for(int j=0;j<BeanRider.tableTitles.length;j++)
				tblRiderData[i][j]=allRider.get(i).getCell(j);
		}
		tabRiderModel.setDataVector(tblRiderData,tblRiderTitle);
		this.dataTableRider.validate();
		this.dataTableRider.repaint();
	}
	
	private void reloadBillTable(){
		try {
			allRiderbills=TakeOutUtil.riderbillManager.loadriderbill(curRider);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRiderbillData =  new Object[allRiderbills.size()][BeanRiderbill.tableTitles.length];
		for(int i=0;i<allRiderbills.size();i++){
			for(int j=0;j<BeanRiderbill.tableTitles.length;j++)
				tblRiderbillData[i][j]=allRiderbills.get(i).getCell(j);
		}
		tabRiderbillModel.setDataVector(tblRiderbillData,tblRiderbillTitle);
		this.dataTableRiderbill.validate();
		this.dataTableRiderbill.repaint();
	}
	
	private void reloadRiderbillTabel(int riderIdx){
		if(riderIdx<0) return;
		curRider=allRider.get(riderIdx);
		try {
			allRiderbills=TakeOutUtil.riderbillManager.loadriderbill(curRider);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblRiderbillData =  new Object[allRiderbills.size()][BeanRiderbill.tableTitles.length];
		for(int i=0;i<allRiderbills.size();i++){
			for(int j=0;j<BeanRiderbill.tableTitles.length;j++)
				tblRiderbillData[i][j]=allRiderbills.get(i).getCell(j);
		}
		tabRiderbillModel.setDataVector(tblRiderbillData,tblRiderbillTitle);
		this.dataTableRiderbill.validate();
		this.dataTableRiderbill.repaint();
	}
    
    public FrmMainManager_Rider() {
    	
    	this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("∆Ô ÷π‹¿Ì");
    	
    	this.btnrideradd.addActionListener(this);
	    this.btnriderdelete.addActionListener(this);
	    this.btnridermodify.addActionListener(this);
	    this.btnorder.addActionListener(this);
    	
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnrideradd);
	    toolBar.add(btnridermodify);
	    toolBar.add(btnriderdelete);
	    toolBar.add(btnorder);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableRider), BorderLayout.WEST);
	    this.dataTableRider.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainManager_Rider.this.dataTableRider.getSelectedRow();
				if(i<0) {
					return;
				}
				FrmMainManager_Rider.this.reloadRiderbillTabel(i);
			}
	    	
	    });
	    this.reloadRiderTable();
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableRiderbill), BorderLayout.CENTER);
	    this.dataTableRiderbill.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainManager_Rider.this.dataTableRiderbill.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnrideradd){
			FrmRiderAdd dlg=new FrmRiderAdd(this,"ÃÌº”∆Ô ÷",true);
			dlg.setVisible(true);
			this.reloadRiderTable();
		}else if(e.getSource()==this.btnriderdelete){
			int i=FrmMainManager_Rider.this.dataTableRider.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "«Î—°‘Ò∆Ô ÷", "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curRider=allRider.get(i);
			}
			try {
				TakeOutUtil.riderManager.deleterider(this.curRider);
				this.reloadRiderTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if(e.getSource()==this.btnridermodify){
			int i=FrmMainManager_Rider.this.dataTableRider.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "«Î—°‘Ò∆Ô ÷", "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curRider=allRider.get(i);
				rider=curRider;
			}
			FrmRiderModify dlg=new FrmRiderModify(this,"±‡º≠∆Ô ÷",true);
			dlg.setVisible(true);
			this.reloadRiderTable();
		}else if(e.getSource()==this.btnorder){
			int i=FrmMainManager_Rider.this.dataTableRider.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "«Î—°‘Ò∆Ô ÷", "¥ÌŒÛ",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curRider=allRider.get(i);
				rider=curRider;
			}
			FrmRiderTakeOrder dlg=new FrmRiderTakeOrder();
			dlg.setVisible(true);
			this.reloadRiderTable();
		}
	}

}
