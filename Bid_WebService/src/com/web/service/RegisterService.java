package com.web.service;


import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import javax.ws.rs.PathParam;

@Path("/registrationServices")
public class RegisterService {
	@Path("/registerUser")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)
	public Response isuserPresent(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		boolean status = false;
		ClientResponse restResponse = null;
		MultivaluedMap fp = new MultivaluedMapImpl();
		try {

			Client client = Client.create();
			WebResource webResource = client.resource("https://localhost:8445/Bid_Microservice/checkUser/lookup");

			String user = formParam.getFirst("username");

			fp.add("username", user);

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
		if(status) {
			return Response.ok().entity(String.valueOf(false)).build();
		}
		else {
			try {
				Client client = Client.create();
				WebResource webResource = client.resource("http://localhost:8082/Bid_Microservice/checkUser/register");
				String pass = formParam.getFirst("password");
				String name = formParam.getFirst("name");
				String address = formParam.getFirst("address");
				String phone = formParam.getFirst("phone");
				String email = formParam.getFirst("email");
				
				fp.add("password", pass);
				fp.add("name", name);
				fp.add("address", address);
				fp.add("phone", phone);
				fp.add("email", email);
				restResponse = webResource
						.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
						.post(ClientResponse.class, fp);

				if (restResponse.getStatus() != 200) {
					throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
				}

				String statusString = restResponse.getEntity(String.class);
				status = Boolean.parseBoolean(statusString);
			}
			catch(Exception ex) {
				ex.printStackTrace();
			}

			return Response.ok().entity(String.valueOf(status)).build();
		}
	}

	@Path("/availableusername/{username}")
	@GET
	public String availableUsername(@PathParam("username") String username) {

		return username + " is not available. Please consider " + username + "_001";
	}
}
