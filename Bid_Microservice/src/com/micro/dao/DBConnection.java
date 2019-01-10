package com.micro.dao;

import java.sql.*;

public class DBConnection {
	public static Connection getConnection() {
		Connection connection = null;
		
		try {			
			Class.forName("com.mysql.jdbc.Driver");
			
			String user = "root";
			String pass = "********";
			String url = "jdbc:mysql://localhost:3306/Bid_System?autoReconnect=true&useSSL=false";
			
			connection = DriverManager.getConnection(url, user, pass);
	
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return connection;
	}

}
