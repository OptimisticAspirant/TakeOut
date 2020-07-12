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
import cn.edu.zucc.takeout.model.BeanCoupon;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmShopCouponControl extends JFrame implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();

    private JButton btncouadd=new JButton("����Ż�ȯ");
    private JButton btncoudelete=new JButton("ɾ���Ż�ȯ");
	
	private Object tblCouTitle[]=BeanCoupon.tableTitles;
	private Object tblCouData[][];
	DefaultTableModel tabCouModel=new DefaultTableModel();
	private JTable dataTableCou=new JTable(tabCouModel);

	private BeanCoupon curCoupon=null;
	List<BeanCoupon> allCoupon=null;

	List<BeanCoupon> shopCous=null;

	private void reloadCouTable(){
		try {
			allCoupon=TakeOutUtil.couponManager.loadShopCoupon(FrmMainManager_Shopkeeper.shop);
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCouData =  new Object[allCoupon.size()][BeanCoupon.tableTitles.length];
		for(int i=0;i<allCoupon.size();i++){
			for(int j=0;j<BeanCoupon.tableTitles.length;j++)
				tblCouData[i][j]=allCoupon.get(i).getCell(j);
		}
		tabCouModel.setDataVector(tblCouData,tblCouTitle);
		this.dataTableCou.validate();
		this.dataTableCou.repaint();
	}
	
	public FrmShopCouponControl() {
		
		reloadCouTable();
		
		this.setTitle("�̼��Ż�ȯ����");
		
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();

		this.btncouadd.addActionListener(this);
	    this.btncoudelete.addActionListener(this);
		
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btncouadd);
	    toolBar.add(btncoudelete);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableCou), BorderLayout.CENTER);

	    this.dataTableCou.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmShopCouponControl.this.dataTableCou.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	}

	@Override
	public void actionPerformed(ActionEvent e) {
    	if(e.getSource()==this.btncouadd){
			FrmCouponAdd dlg=new FrmCouponAdd(this,"����Ż�ȯ",true);
			dlg.setVisible(true);
		    this.reloadCouTable();
		}else if(e.getSource()==this.btncoudelete){
			int i=FrmShopCouponControl.this.dataTableCou.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "��ѡ���Ż�ȯ", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
			    this.reloadCouTable();
				curCoupon=allCoupon.get(i);
			}
			try {
				TakeOutUtil.couponManager.deletecoup(this.curCoupon);
			    this.reloadCouTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
	
}
