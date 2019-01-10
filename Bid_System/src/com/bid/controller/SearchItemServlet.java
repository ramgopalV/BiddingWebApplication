package com.bid.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.util.*;
import java.io.*;
import org.json.JSONArray;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

/**
 * Servlet implementation class SearchItemServlet
 */
@WebServlet("/SearchItemServlet")
public class SearchItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		Boolean status = false;
		JSONArray profileArray = null;
		PrintWriter out = null;
		String startDate = request.getParameter("startDate");
		String endDate = request.getParameter("endDate");
		
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
			
			MultivaluedMap formData = new MultivaluedMapImpl();
			//formData.add("startDate", (sdf.format(new SimpleDateFormat("mm/dd/yyyy").parse(startDate))).toString());
			//formData.add("endDate", (sdf.format(new SimpleDateFormat("mm/dd/yyyy").parse(endDate))).toString());
			formData.add("startDate", startDate);
			formData.add("endDate", endDate);
			
			Client client = Client.create();
			WebResource webResource = client.resource("https://localhost:8445/Bid_Microservice/fetchAllItems/fetchItems");
			ClientResponse restResponse = webResource
					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
			
			profileArray = new JSONArray(restResponse.getEntity(String.class));
			
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
			out = response.getWriter();
			 
			out.write(profileArray.toString());
			out.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
