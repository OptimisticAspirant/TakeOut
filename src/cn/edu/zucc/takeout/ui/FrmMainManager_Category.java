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
import cn.edu.zucc.takeout.model.BeanProductcategory;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainManager_Category extends JFrame implements ActionListener{
	private static final long serialVersionUID = 1L;
	
	private JMenuBar menubar=new JMenuBar();
	
	private JPanel toolBar=new JPanel();
	
	private JButton btncateadd=new JButton("添加商品分类");
    private JButton btncatemodify=new JButton("修改商品分类");
    private JButton btncatedelete=new JButton("删除商品分类");
    
    private Object tblCateTitle[]=BeanProductcategory.tableTitles;
	private Object tblCateData[][];
	DefaultTableModel tabCateModel=new DefaultTableModel();
	private JTable dataTableCate=new JTable(tabCateModel);
	
	private BeanProductcategory curCate=null;
	List<BeanProductcategory> allCate=null;
	public static BeanProductcategory ccccategory=null;
	
	private void reloadCateTable(){
		try {
			allCate=TakeOutUtil.categoryManager.loadAll();
		} catch (BaseException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
			return;
		}
		tblCateData =  new Object[allCate.size()][BeanProductcategory.tableTitles.length];
		for(int i=0;i<allCate.size();i++){
			for(int j=0;j<BeanProductcategory.tableTitles.length;j++)
				tblCateData[i][j]=allCate.get(i).getCell(j);
		}
		tabCateModel.setDataVector(tblCateData,tblCateTitle);
		this.dataTableCate.validate();
		this.dataTableCate.repaint();
	}
	
	public FrmMainManager_Category() {
		
		this.setTitle("商品分类管理");
		
		this.setSize(480, 360);
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);
		this.validate();
		
	    this.btncateadd.addActionListener(this);
	    this.btncatemodify.addActionListener(this);
	    this.btncatedelete.addActionListener(this);
	    
	    toolBar.setLayout(new FlowLayout(FlowLayout.LEFT));
	    menubar.add(toolBar);
	    toolBar.add(btncateadd);
	    toolBar.add(btncatedelete);
	    toolBar.add(btncatemodify);
	    this.setJMenuBar(menubar);
	    
	    this.getContentPane().add(new JScrollPane(this.dataTableCate), BorderLayout.CENTER);
	    this.dataTableCate.addMouseListener(new MouseAdapter (){

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=FrmMainManager_Category.this.dataTableCate.getSelectedRow();
				if(i<0) {
					return;
				}
			}
	    	
	    });
	    this.reloadCateTable();

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {

		if(e.getSource()==this.btncateadd){
			FrmCategoryAdd dlg=new FrmCategoryAdd(this,"添加商品类别",true);
			dlg.setVisible(true);
			this.reloadCateTable();
		}else if(e.getSource()==this.btncatedelete){
			int i=FrmMainManager_Category.this.dataTableCate.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curCate=allCate.get(i);
			}
			try {
				TakeOutUtil.categoryManager.deletecate(this.curCate);
				this.reloadCateTable();
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}else if(e.getSource()==this.btncatemodify){
			int i=FrmMainManager_Category.this.dataTableCate.getSelectedRow();
			if(i<0) {
				JOptionPane.showMessageDialog(null, "请选择商品类别", "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}else {
				curCate=allCate.get(i);
				ccccategory=curCate;
			}
			FrmCategoryModify dlg=new FrmCategoryModify(this, "修改商品类名", true);
			dlg.setVisible(true);
			this.reloadCateTable();
		}
		
	}

}
