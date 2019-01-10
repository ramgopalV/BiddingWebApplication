package com.micro.service;

import java.sql.ResultSet;
import com.micro.dao.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.util.*;
import com.micro.dao.DBQuery;
import org.json.*;
import java.text.*;
import java.util.Calendar;

@Path("/fetchMyItem")
public class FetchItemMicro {
	@Path("/fetchItem/{username}")
    @Produces("application/json")
	@GET
	public Response fetchItem(@PathParam("username") String username) throws JSONException{
		JSONArray profileArray = new JSONArray();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		try {
			ResultSet rs = DBQuery.getResult("select itemTitle, itemDesc, auctionDate, auctionTime, biddingPrice from item where username='" + username + "' and marker = '0' and auctionDate >= '" + dateFormat.format(date) + "'");
			
			while(rs.next()) {	
				JSONObject obj = new JSONObject();
				
				obj.put("itemTitle", rs.getString("itemTitle"));
				obj.put("itemDesc", rs.getString("itemDesc"));
				obj.put("auctionDate", rs.getString("auctionDate"));
				obj.put("auctionTime", rs.getString("auctionTime"));
				obj.put("biddingPrice", rs.getString("biddingPrice"));
				
				profileArray.put(obj.toString());
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(profileArray.toString()).build();
	}
}
