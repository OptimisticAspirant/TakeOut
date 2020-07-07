package cn.edu.zucc.takeout.util;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.util.HashSet;
import java.util.Set;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class DBUtil {
	private static ComboPooledDataSource ds=null;
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ds=new ComboPooledDataSource();
		ds.setMaxPoolSize(15);
		ds.setUser("root");
		ds.setPassword("123456");
		ds.setJdbcUrl("jdbc:mysql://localhost:3306/takeout?useSSL=false");
		try {
			ds.setDriverClass("com.mysql.jdbc.Driver");
		} catch (PropertyVetoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private static Set<Connection> conns=new HashSet<>();
	private static int conncount=0;
	public static synchronized Connection getConnection() throws java.sql.SQLException{
		return ds.getConnection();
	}
	protected static void freeConnection(Connection conn) {
		conns.add(conn);
	}
}
