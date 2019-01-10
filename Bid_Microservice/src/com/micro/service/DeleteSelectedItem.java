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

@Path("/deleteSelectedItem")
public class DeleteSelectedItem {
	@Path("/deleteItem")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	
	public Response deleteItem(MultivaluedMap<String, String> formParam) {
		
		String itemTitle = String.valueOf(formParam.getFirst("itemTitle"));
		String itemDesc = String.valueOf(formParam.getFirst("itemDesc"));
		String auctionDate = String.valueOf(formParam.getFirst("auctionDate"));
		String auctionTime = String.valueOf(formParam.getFirst("auctionTime"));
		String str = "";
		
		try {
			DBQuery.insertResult("update item set marker = '1' where itemTitle = '" + itemTitle + "' and itemDesc = '" + itemDesc + "' and auctionDate = '" + auctionDate + "' and auctionTime = '" + auctionTime +"'");
			
			ResultSet rs = DBQuery.getResult("select slots from auctionDuration where auctionDate='" + auctionDate + "'");
			
			while(rs.next()) {	
				str =  rs.getString("slots");
			}
			str = str + auctionTime;
			
			DBQuery.insertResult("update auctionDuration set slots = '" + str + "' where auctionDate = '" + auctionDate + "'");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok().entity(String.valueOf(true)).build();
	}
	
}
