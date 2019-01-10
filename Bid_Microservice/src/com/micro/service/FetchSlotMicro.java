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

@Path("/fetchDateSlot")
public class FetchSlotMicro {
	@Path("/fetchSlot/{date}")
    @Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response fetchSlot(@PathParam("date") String date){
		String str = "";
		try {
			ResultSet rs = DBQuery.getResult("select slots from auctionDuration where auctionDate='" + date + "'");
			
			while(rs.next()) {	
				str =  rs.getString("slots");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(str).build();
	}
}
