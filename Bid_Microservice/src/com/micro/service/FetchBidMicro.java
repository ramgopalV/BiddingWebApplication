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

@Path("/fetchCurrentBid")
public class FetchBidMicro {
	@Path("/fetchBid")
	@Produces("application/json")
	@GET
	public Response fetchProfile() throws JSONException{
		JSONArray profileArray = new JSONArray();
		JSONObject obj = new JSONObject();

		try {
			Calendar cal = Calendar.getInstance();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
			String currentTime = sdf.format(cal.getTime());
			String[] minute = currentTime.split(":");
			int min = (Integer.parseInt(minute[1])/15)*15;
			String time = "";

			if(min == 0) {
				time = minute[0] + ":00:00";
			}
			else {
				time = minute[0] + ":" + min + ":00";
			}

			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
						
			ResultSet rs = DBQuery.getResult("select itemID, itemTitle, itemDesc, biddingPrice, file from item where auctionTime = '" + time + "' and marker = '0' and auctionDate = '" + dateFormat.format(date) + "'");

			if (rs.next()) {
				rs.beforeFirst();

				while(rs.next()) {	

					ResultSet result = DBQuery.getResult("select biddingPrice from bidresult where itemID = '" + rs.getString("itemID") + "'");

					obj.put("itemTitle", rs.getString("itemTitle"));
					obj.put("itemDesc", rs.getString("itemDesc"));
					obj.put("initialBid", rs.getString("biddingPrice"));

					if (result.next()) {
						result.beforeFirst();
						
						while(result.next()) {
							obj.put("biddingPrice", result.getString("biddingPrice"));
						}
					} 
					else {
						obj.put("biddingPrice", rs.getString("biddingPrice"));
					}
					obj.put("file", rs.getString("file"));
				}
			}

			profileArray.put(obj);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(profileArray.toString()).build();
	}
}
