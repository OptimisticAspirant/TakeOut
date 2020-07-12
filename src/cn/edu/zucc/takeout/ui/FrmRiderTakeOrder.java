package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
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
import cn.edu.zucc.takeout.model.BeanCustomer;
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmRiderTakeOrder extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
    
    private Object tblOrdTitle[]=BeanProductorder.tableTitles;
	private Object tblOrdData[][];
	DefaultTableModel tabOrdModel=new DefaultTableModel();
	private JTable dataTableOrd=new JTable(tabOrdModel);
	
	private BeanProductorder curOrder=null;
	List<BeanProductorder> allOrder=null;
	
	private void reloadOrdTable(){
		try {
			allOrder=TakeOutUtil.orderManager.loadAllOrders();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblOrdData = new Object[allOrder.size()][BeanProductorder.tableTitles.length];
		for(int i=0;i<allOrder.size();i++){
			for(int j=0;j<BeanProductorder.tableTitles.length;j++)
				tblOrdData[i][j]=allOrder.get(i).getCell(j);
		}
		tabOrdModel.setDataVector(tblOrdData,tblOrdTitle);
		this.dataTableOrd.validate();
		this.dataTableOrd.repaint();
	}
	
	public FrmRiderTakeOrder() {
		
		this.setTitle("分配订单");
		this.setSize(720, 480);
		
		reloadOrdTable();
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
		this.getContentPane().add(new JScrollPane(this.dataTableOrd), BorderLayout.CENTER);
	    this.dataTableOrd.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmRiderTakeOrder.this.dataTableOrd.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnOk){
			int i=FrmRiderTakeOrder.this.dataTableOrd.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择订单", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curOrder=allOrder.get(i);
				try {
					TakeOutUtil.orderManager.assignOrder(FrmMainManager_Rider.rider,curOrder);
					reloadOrdTable();
				} catch (BaseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}else if(e.getSource()==this.btnCancel){
			this.setVisible(false);
			return;
		}
	}
	
	
}
