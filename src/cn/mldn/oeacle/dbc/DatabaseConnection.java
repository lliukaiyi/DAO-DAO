package cn.mldn.oeacle.dbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	private static final String DBDRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String DBURL = "jdbc:oracle:thin:@localhost:1521:ORCL";
	private static final String DBUSER = "scott";
	private static final String PASSWORD ="123456";
	private static ThreadLocal<Connection> threadlocal = new ThreadLocal<Connection>();
	private DatabaseConnection(){
		
	}
	public static Connection rConnection(){
		try {
			Class.forName(DBDRIVER);
			return DriverManager.getConnection(DBURL, DBUSER, PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Connection getConnection(){
		Connection conn = threadlocal.get();
		if(conn == null){
			conn = rConnection();
			threadlocal.set(conn);
		}
		return conn;
	}
	public static void colse(){
		Connection conn = threadlocal.get();
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		threadlocal.remove();
	}

}
