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
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainManager_Rider extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btnrideradd=new JButton("添加骑手");
    private JButton btnriderdelete=new JButton("删除骑手");
    private JButton btnorder=new JButton("分配订单");
    
	private Object tblRiderTitle[]=BeanRider.tableTitles;
	private Object tblRiderData[][];
	DefaultTableModel tabRiderModel=new DefaultTableModel();
	private JTable dataTableRider=new JTable(tabRiderModel);

	private BeanRider curRider=null;
	List<BeanRider> allRider=null;
	
	private void reloadRiderTable(){//rider
		try {
			allRider=TakeOutUtil.riderManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
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
    
    public FrmMainManager_Rider() {
    	
    	this.setExtendedState(Frame.MAXIMIZED_BOTH);
		this.setTitle("骑手管理");
    	
    	this.btnrideradd.addActionListener(this);
	    this.btnriderdelete.addActionListener(this);
	    this.btnorder.addActionListener(this);
    	
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnrideradd);
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
			}
	    	
	    });
	    this.reloadRiderTable();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnrideradd){
			FrmAddRider dlg=new FrmAddRider(this,"添加骑手",true);
			dlg.setVisible(true);
			this.reloadRiderTable();
		}else if(e.getSource()==this.btnriderdelete){
			int i=FrmMainManager_Rider.this.dataTableRider.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择骑手", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curRider=allRider.get(i);
			}
			try {
				TakeOutUtil.riderManager.deleterider(this.curRider);
				this.reloadRiderTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

}
