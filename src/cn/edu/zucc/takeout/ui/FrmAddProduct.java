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


public class FrmAddProduct extends JDialog implements ActionListener{
	
	public BeanShopkeeper shop=null;
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelID = new JLabel("��Ʒ��ţ�");
	private JLabel labelCateId = new JLabel("��Ʒ���ࣺ");
	private JLabel labelCatename = new JLabel("��Ʒ���ƣ�");
	private JLabel labelPrice = new JLabel("��Ʒ�۸�");
	private JLabel labelSale = new JLabel("�Żݼ۸�");
	
	private JTextField edtID = new JTextField(20);
	private JComboBox edtCateId=new JComboBox();

	private JTextField edtCatename = new JTextField(20);
	private JTextField edtPrice = new JTextField(20);
	private JTextField edtSale = new JTextField(20);
	public FrmAddProduct(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelID);
		workPane.add(edtID);
		workPane.add(labelCateId);
		workPane.add(edtCateId);
		edtCateId.addItem("---��ѡ��---");
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
		this.setSize(360, 180);
		// ��Ļ������ʾ
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
		if(e.getSource()==this.btnCancel) {
			this.setVisible(false);
			return;
		}
		else if(e.getSource()==this.btnOk){
			String proid=this.edtID.getText();
			String category=this.edtCateId.getToolTipText();
			String proname=this.edtCatename.getToolTipText();
			Float price=Float.parseFloat(this.edtPrice.getText());
			Float sale=Float.parseFloat(this.edtSale.getText());
			try {
				TakeOutUtil.productManager.add(new FrmMainManager_Shopkeeper().shop,proid,category,proname,price,sale);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
		
		}
		
	}
	
}
