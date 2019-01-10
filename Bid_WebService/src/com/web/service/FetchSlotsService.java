package com.web.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import org.json.JSONArray;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import javax.ws.rs.PathParam;

@Path("/fetchAvailableSlots")
public class FetchSlotsService {
	
	@Path("/fetchSlots/{date}")
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public Response fetchSlot(@PathParam("date") String date) {
		
		ClientResponse restResponse = null;
		
		try {

			Client client = Client.create();
			WebResource webResource = client.resource("https://localhost:8445/Bid_Microservice/fetchDateSlot/fetchSlot/" + date);
			
			restResponse = webResource
					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class);
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return Response.status(200).entity(restResponse.getEntity(String.class)).build();
	}
}
