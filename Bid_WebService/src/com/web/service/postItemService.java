package com.web.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.*;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.PathParam;


@Path("/postItemServices")
public class postItemService {
	@Path("/item")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response isValidUser(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status = false;
		ClientResponse restResponse = null;
		
		MultivaluedMap fp = new MultivaluedMapImpl();
		try {
			Client client = Client.create();
			WebResource webResource = client.resource("https://localhost:8445/Bid_Microservice/postItem/item");
			
			String file = formParam.getFirst("file");
			String itemName = formParam.getFirst("item");
			String description = formParam.getFirst("description");
			String price = formParam.getFirst("price");
			String date = formParam.getFirst("date");
			String time = formParam.getFirst("time");
			String user = formParam.getFirst("user");
			
			fp.add("file", file);
			fp.add("item", itemName);
			fp.add("description", description);
			fp.add("price", price);
			fp.add("date", date);
			fp.add("time", time);
			fp.add("user", user);
			
			restResponse = webResource
					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, fp);

			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}

			String statusString = restResponse.getEntity(String.class);
			status = Boolean.parseBoolean(statusString);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		//if(!status) {
			return Response.ok().entity(String.valueOf(status)).build();
		//}
		
	}

	@Path("/availableusername/{username}")
	@GET
	public String availableUsername(@PathParam("username") String username) {

		return username + " is not available. Please consider " + username + "_001";
	}
}
