package com.micro.dao;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.*;
import java.util.*;
import javax.servlet.http.Part;
import java.text.SimpleDateFormat;  

public class DBQuery {

	public static ResultSet getResult(String sql) {
		ResultSet resultSet = null;
		try {
			Connection connection = DBConnection.getConnection();

			Statement stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return resultSet;
	}

	public static void insertResult(String sql) {
		try {
			Connection connection = DBConnection.getConnection();

			Statement stmt = connection.createStatement();
			stmt.executeUpdate(sql);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void insertItem(String user, String item, String desc, float price, String date, String time, String file) {
		try {
			byte[] decoded = Base64.getDecoder().decode(file);
			InputStream inputStream = new ByteArrayInputStream(decoded);
			String sql = "insert into item (username, itemTitle, itemDesc, biddingPrice, auctionDate, auctionTime, file, marker) values (?, ?, ?, ?, ?, ?, ?, ?)"; 

			SimpleDateFormat outSDF = new SimpleDateFormat("yyyy-mm-dd");
			SimpleDateFormat inSDF = new SimpleDateFormat("mm/dd/yyyy");
			java.util.Date d1 = inSDF.parse(date);


			String s = outSDF.format(d1);

			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(sql);

			preparedStmt.setString(1, user);
			preparedStmt.setString(2, item);
			preparedStmt.setString(3, desc);
			preparedStmt.setFloat(4, price);
			preparedStmt.setDate(5, java.sql.Date.valueOf(s));
			preparedStmt.setTime(6, java.sql.Time.valueOf(time));
			preparedStmt.setBlob(7, inputStream);
			preparedStmt.setBoolean(8, false);

			System.out.println("user : " + user);
			System.out.println("item : " + item);
			System.out.println("desc : "+ desc);
			System.out.println("price : " + price);
			System.out.println("date : " + java.sql.Date.valueOf(s));
			System.out.println("time : " + java.sql.Time.valueOf(time));

			preparedStmt.execute();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void insertBid(String username, int itemID, float newBidValue) {
		try {
			String sql = "insert into bid (username, itemID, biddingPrice) values (?, ?, ?)"; 
			Connection connection = DBConnection.getConnection();
			PreparedStatement preparedStmt = connection.prepareStatement(sql);

			preparedStmt.setString(1, username);
			preparedStmt.setInt(2, itemID);
			preparedStmt.setFloat(3, newBidValue);
			
			preparedStmt.execute();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
