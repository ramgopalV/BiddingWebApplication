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
import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Path("/fetchBidSummary")
public class FetchBidSummaryMicro {
	@Path("/fetchSummary")
	@Produces("application/json")
	@GET
	public Response fetchProfile() throws JSONException{
		JSONArray profileArray = new JSONArray();

		try {
			ResultSet rs = DBQuery.getResult("select itemTitle, itemDesc, item.username as owner, bidresult.username as winner, item.biddingPrice as initialbid, bidresult.biddingPrice as finalbid from item, bidresult where item.itemID = bidresult.itemID");

			if (rs.next()) {
				rs.beforeFirst();

				while(rs.next()) {	
					JSONObject obj = new JSONObject();
					
					obj.put("itemTitle", rs.getString("itemTitle"));
					obj.put("itemDesc", rs.getString("itemDesc"));
					obj.put("owner", rs.getString("owner"));
					obj.put("winner", rs.getString("winner"));
					obj.put("initialbid", rs.getString("initialbid"));
					obj.put("finalbid", rs.getString("finalbid"));
					
					profileArray.put(obj);
				}
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(profileArray.toString()).build();
	}
}
