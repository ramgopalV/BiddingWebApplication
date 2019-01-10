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

@Path("/fetchUserProfile")
public class FetchProfileMicro {
	@Path("/fetchProfile/{username}")
    @Produces("application/json")
	@GET
	public Response fetchProfile(@PathParam("username") String username) throws JSONException{
		JSONArray profileArray = new JSONArray();
		JSONObject obj = new JSONObject();
		
		try {
			ResultSet rs = DBQuery.getResult("select username, name, email, phone, address from user where username='" + username + "'");
			
			while(rs.next()) {	
				obj.put("USERNAME", rs.getString("username"));
				obj.put("name", rs.getString("name"));
				obj.put("email", rs.getString("email"));
				obj.put("phone", rs.getString("phone"));
				obj.put("address", rs.getString("address"));
			}
			profileArray.put(obj);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.status(200).entity(profileArray.toString()).build();
	}
}
