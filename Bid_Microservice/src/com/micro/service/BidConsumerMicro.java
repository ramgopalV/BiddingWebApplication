package com.micro.service;

import com.micro.dao.*;
import java.text.*;
import java.sql.ResultSet;
import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import java.io.InputStream;
import java.io.ByteArrayInputStream;
import org.json.*;
import org.json.simple.parser.*; 

@Path("/bidConsumerService")
public class BidConsumerMicro {
	@Path("/bidConsumer")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	public void bidConsumer(String str) {
		try {
			JSONObject jsonArray = new JSONObject(str);
			JSONObject json = new JSONObject(jsonArray.getString("message"));
			
			String username = json.getString("username");
			String newBidValue = json.getString("newBidValue");
			String itemName = json.getString("itemName");
			String itemID = "";
			float biddingPrice = 0;

			ResultSet rs = DBQuery.getResult("select itemID from item where itemTitle = '" + itemName + "'");

			while(rs.next()) {	
				itemID = rs.getString("itemID");
			}			
			DBQuery.insertBid(username, Integer.parseInt(itemID), Float.parseFloat(newBidValue));
			
			ResultSet result = DBQuery.getResult("select biddingPrice from bidresult where itemID = '" + itemID + "'");
			
			if (result.first()) {
				result.beforeFirst();
				while(result.next()) {	
					biddingPrice = Float.parseFloat(result.getString("biddingPrice"));
				}
				
				if(biddingPrice < Float.parseFloat(newBidValue)) {
					DBQuery.insertResult("update bidresult set biddingPrice = '" + Float.parseFloat(newBidValue) + "' where itemID = '" + Integer.parseInt(itemID) + "'");
				}
			} 
			else {
				DBQuery.insertResult("insert into bidresult values ('" + username + "','" + Integer.parseInt(itemID) + "','" + Float.parseFloat(newBidValue) + "')");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

	}

}
