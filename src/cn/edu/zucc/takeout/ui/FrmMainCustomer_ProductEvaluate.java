package cn.edu.zucc.takeout.ui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import cn.edu.zucc.takeout.TakeOutUtil;
import cn.edu.zucc.takeout.util.BaseException;
import cn.edu.zucc.takeout.util.BusinessException;

public class FrmMainCustomer_ProductEvaluate extends JDialog implements ActionListener{
	
	private JPanel toolBar = new JPanel();
	private JPanel workPane = new JPanel();
	private Button btnOk = new Button("ȷ��");
	private Button btnCancel = new Button("ȡ��");
	private JLabel labelContent = new JLabel("���");
	private JLabel labelStar = new JLabel("�Ǽ���");
	
	private JTextArea edtContent = new JTextArea(4,20);
	private JTextField edtStar = new JTextField(20);
	
	public FrmMainCustomer_ProductEvaluate(JFrame f, String s, boolean b) {
		super(f, s, b);
		toolBar.setLayout(new FlowLayout(FlowLayout.RIGHT));
		toolBar.add(btnOk);
		toolBar.add(btnCancel);
		this.getContentPane().add(toolBar, BorderLayout.SOUTH);
		workPane.add(labelStar);
		workPane.add(edtStar);
		workPane.add(labelContent);
		workPane.add(edtContent);
		this.getContentPane().add(workPane, BorderLayout.CENTER);
		this.setSize(300, 200);
		
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
			String content=this.edtContent.getText();
			float star=0;
			try {
				star=Float.parseFloat(this.edtStar.getText());
			} catch (NumberFormatException e2) {
				JOptionPane.showMessageDialog(null, "���������֣�", "����",JOptionPane.ERROR_MESSAGE);
				return;
			}
			try {
				TakeOutUtil.orderManager.evaluateProduct(FrmMainCustomer_Orders.detail, content, star);
			} catch (BaseException e1) {
				e1.printStackTrace();
			}
			this.setVisible(false); 
		}
		
	}
}
