package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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
import cn.edu.zucc.takeout.model.BeanProductorder;
import cn.edu.zucc.takeout.util.BaseException;

public class FrmMainCustomer_RiderEvaluate extends JDialog implements ActionListener{
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("确定");
	private Button btnCancel = new Button("取消");
	private JLabel labelEva = new JLabel("选择评价：");
	private JComboBox edtEva=new JComboBox();
	
	public FrmMainCustomer_RiderEvaluate(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelEva);
		workPane.add(edtEva);
		edtEva.addItem("--请选择评价--");
		List<String> evaList=new ArrayList<String>();
		evaList.add("好评");
		evaList.add("差评");
		for(int i=0;i<evaList.size();i++) {
			edtEva.addItem(evaList.get(i));
		}
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(320, 100);
		
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
			List<String> evaList=new ArrayList<String>();
			evaList.add("好评");
			evaList.add("差评");
			for(int i=0;i<evaList.size();i++) {
				edtEva.addItem(evaList.get(i));
			}
			int selectedId=this.edtEva.getSelectedIndex();
			String evaluate=evaList.get(selectedId-1);
			try {
				TakeOutUtil.orderManager.evaluateRider(FrmMainCustomer_Orders.order, evaluate);
				this.setVisible(false);
			} catch (BaseException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "错误",JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
		
	}
}
