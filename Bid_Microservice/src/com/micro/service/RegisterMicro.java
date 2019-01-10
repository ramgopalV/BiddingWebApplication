package com.micro.service;

import com.micro.dao.*;
import java.sql.ResultSet;
import java.sql.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("/checkUser")
public class RegisterMicro {
	@Path("/lookup")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	
	public Response checkUsername(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		String user = formParam.getFirst("username");
		
		try {
			ResultSet rs = DBQuery.getResult("select count(*) as count from access where username='" + user + "'");
			String p = "";
			
			while(rs.next()) {
				p = rs.getString("count");
			}
			if(p.equals("1")){
				response = true;
			}
			else{
				response = false;
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println(response);
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	
	
	@Path("/register")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response addUser(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		String user = formParam.getFirst("username");
		String password = formParam.getFirst("password");
		String name = formParam.getFirst("name");
		String email = formParam.getFirst("email");
		String phone = formParam.getFirst("phone");
		String address = formParam.getFirst("address");
		
		try {
			DBQuery.insertResult("insert into access values('" + user + "', 'normal' ,'" + password + "')");
			DBQuery.insertResult("insert into user values('" + user + "','" + name + "','" + email + "','"  + phone +  "','"  + address + "')");
			
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok().entity(String.valueOf(true)).build();
	}
}
