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

@Path("/fetchAllItemBid")
public class FetchAllItemBidMicro {
	@Path("/fetchAllBid")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public Response fetchAllItems(MultivaluedMap<String, String> formParam) throws JSONException{
		JSONArray profileArray = new JSONArray();
		String itemID = "";
		String itemName = formParam.getFirst("itemName");
				
		try {
			ResultSet rs = DBQuery.getResult("select itemID from item where itemTitle = '" + itemName + "'");
			
			if (rs.next()) {
				rs.beforeFirst();
				
				while(rs.next()) {	
					itemID = rs.getString("itemID");
				}
				
				ResultSet result = DBQuery.getResult("select username, biddingPrice from bid where itemID = '" + itemID + "'");
				
				if (result.next()) {
					result.beforeFirst();
					
					while(result.next()) {
						JSONObject obj = new JSONObject();
						obj.put("itemName", itemName);
						obj.put("username", result.getString("username"));
						obj.put("biddingPrice", result.getString("biddingPrice"));
						profileArray.put(obj.toString());
					}
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(profileArray.toString()).build();
	}
}
