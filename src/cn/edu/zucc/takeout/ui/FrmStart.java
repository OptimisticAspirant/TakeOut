package cn.edu.zucc.takeout.ui;

import javax.swing.JFrame;

public class FrmStart extends JFrame{
	private static final long serialVersionUID = 1L;
	public static FrmLogin dlgLogin=null;
	
	public FrmStart(){
		dlgLogin=new FrmLogin(this,"��½",true);
		dlgLogin.setVisible(true);
	}
}
