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

@Path("/fetchAllItems")
public class FetchAllItems {
	@Path("/fetchItems")
	@Produces("application/json")
	@Consumes("application/x-www-form-urlencoded")
	@POST
	public Response fetchAllItems(MultivaluedMap<String, String> formParam) throws JSONException{
		JSONArray profileArray = new JSONArray();

		String startDate = formParam.getFirst("startDate");
		String endDate = formParam.getFirst("endDate");
		
		try {
			ResultSet rs = DBQuery.getResult("select itemTitle, itemDesc, auctionDate, auctionTime, biddingPrice from item where auctionDate >='" + startDate + "' and auctionDate <= '" + endDate + "' and marker = 'false'");

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
