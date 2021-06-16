package DAO;

import java.sql.*;

public class ConnectionDB {
	public static final String JDBC_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	public static final String DB_URL = "jdbc:sqlserver://localhost;databaseName=QuanLyBanHang;instance=MSSQLSERVER";
	public static final String USER = "sa";
	public static final String PASS = "1";
	
	
	public static final String dbURL = "jdbc:sqlserver://localhost:1433;databaseName=QuanLyBanHang;integratedSecurity=true;";
	
	public static Connection conn = null;
	
	public static boolean connect() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Successfully load driver!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: unable to load driver class.");
			return false;
		}
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			System.out.println("Successfully connect to database");		
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean connect_() {
		try {
			Class.forName(JDBC_DRIVER);
			System.out.println("Successfully load driver!");
		} catch (ClassNotFoundException e) {
			System.out.println("Error: unable to load driver class.");
			return false;
		}
		
		try {
            conn = DriverManager.getConnection(dbURL);
			System.out.println("Successfully connect to database");		
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean closeConnect() {
		try {
			if (conn != null)
				conn.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Statement stm = null;
		try {
			if(ConnectionDB.connect_()) {
				String sql = "SELECT * FROM SanPham WHERE MaSP >= 1 AND MaSP <= 50";
				stm = conn.createStatement();
				ResultSet rs = stm.executeQuery(sql);
				while (rs.next()) {
					int id = rs.getInt("MaSP");
					String name = rs.getString("TenSP");
					int number = rs.getInt("SoLuongTon");
					long price = rs.getLong("Gia");
					System.out.println(id + "\t" + name + "\t" + number + "\t" + price);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
