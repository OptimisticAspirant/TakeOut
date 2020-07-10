package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.control.ProductManager;
import cn.edu.zucc.takeout.model.BeanShopkeeper;
import cn.edu.zucc.takeout.util.BaseException;


public class FrmProductAdd extends JDialog implements ActionListener{
	
	public BeanShopkeeper shop=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelID = new JLabel("商品编号：");
	private JLabel labelCateId = new JLabel("商品分类：");
	private JLabel labelCatename = new JLabel("商品名称：");
	private JLabel labelPrice = new JLabel("商品价格：");
	private JLabel labelSale = new JLabel("优惠价格：");
	
	private JTextField edtID = new JTextField(15);
	private JComboBox edtCateId=new JComboBox();
	private JTextField edtCatename = new JTextField(15);
	private JTextField edtPrice = new JTextField(15);
	private JTextField edtSale = new JTextField(15);
	public FrmProductAdd(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelID);
		workPane.add(edtID);
		workPane.add(labelCateId);
		workPane.add(edtCateId);
		edtCateId.addItem("-----请选择商品类别------");
		List<String> cateList=null;
		try {
			cateList = new ProductManager().loadProCate();
		} catch (BaseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i=0;i<cateList.size();i++) {
			edtCateId.addItem(cateList.get(i));
		}
		workPane.add(labelCatename);
		workPane.add(edtCatename);
		workPane.add(labelPrice);
		workPane.add(edtPrice);
		workPane.add(labelSale);
		workPane.add(edtSale);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(280, 220);
		// 屏幕居中显示
		double width = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		double height = Toolkit.getDefaultToolkit().getScreenSize().getHeight();
		this.setLocation((int) (width - this.getWidth()) / 2,
				(int) (height - this.getHeight()) / 2);

		this.validate();
		this.btnOk.addActionListener(this);
		this.btnCancel.addActionListener(this);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		List<String> cateIdList=null;
		try {
			cateIdList=new ProductManager().loadProCateID();
		} catch (BaseException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			int proid=Integer.parseInt(this.edtID.getText());
			int selectedId=this.edtCateId.getSelectedIndex();
			String category=cateIdList.get(selectedId-1);
			String proname=this.edtCatename.getText();
			Float price=Float.parseFloat(this.edtPrice.getText());
			Float sale=Float.parseFloat(this.edtSale.getText());
			try {
				TakeOutUtil.productManager.add(new FrmMainManager_Shopkeeper().shop,proid,category,proname,price,sale);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
}
