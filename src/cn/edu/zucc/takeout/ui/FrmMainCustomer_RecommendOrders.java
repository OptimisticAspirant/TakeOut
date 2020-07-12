package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
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
import cn.edu.zucc.takeout.model.BeanProduct;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer_RecommendOrders extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();
	
	private JButton btnaddtocart=new JButton("���빺�ﳵ");
	
	private BeanProduct curPro=null;
	List<BeanProduct> allPro=null;
	
	private String tblProTitle[]=new String[]{"��Ʒ���","��Ʒ����","��Ʒ����","��Ʒ�۸�","�Żݼ۸�","�̼ұ��"};
	private Object tblProData[][];
	DefaultTableModel tabProModel=new DefaultTableModel();
	private JTable dataTablePro=new JTable(tabProModel);
	
	private void reloadProTable(){
		try {
			allPro=TakeOutUtil.recommendManager.loadPros();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblProData = new Object[allPro.size()][6];
		for(int i=0;i<allPro.size();i++){
			for(int j=0;j<6;j++)
				tblProData[i][j]=allPro.get(i).getCell(j);
		}
		tabProModel.setDataVector(tblProData,tblProTitle);
		this.dataTablePro.validate();
		this.dataTablePro.repaint();
	}
	
	public FrmMainCustomer_RecommendOrders() {

		this.setTitle("�Ƽ��˵�");
		
		this.setSize(720, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		
		reloadProTable();
    
	    this.btnaddtocart.addActionListener(this);
	    
		toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btnaddtocart);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTablePro), BorderLayout.CENTER);
	    this.dataTablePro.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainCustomer_RecommendOrders.this.dataTablePro.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource()==this.btnaddtocart){
			int i=FrmMainCustomer_RecommendOrders.this.dataTablePro.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ����Ʒ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curPro=allPro.get(i);
				int flag=0;
				for(int k=0;k<FrmMainCustomer.cartList.size();k++) {
					if(FrmMainCustomer.cartList.get(k).getPro_id()==curPro.getPro_id()) {
						flag=1;
						BeanProduct temp=FrmMainCustomer.cartList.get(k);
						temp.setCount(temp.getCount()+1);
						FrmMainCustomer.cartList.remove(k);
						FrmMainCustomer.cartList.add(temp);
					}
				}
				if(flag==0) {
					FrmMainCustomer.cartList.add(curPro);
				}
			}
		}
	}
	
	
}
