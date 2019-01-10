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
@WebServlet("/DeleteItemServlet")
public class DeleteItemServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteItemServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		String itemTitle = request.getParameter("itemTitle");
		String itemDesc = request.getParameter("itemDesc");
		String auctionDate = request.getParameter("auctionDate");
		String auctionTime = request.getParameter("auctionTime");
		
		try {			
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("itemTitle", itemTitle);
			formData.add("itemDesc", itemDesc);
			formData.add("auctionDate", auctionDate);
			formData.add("auctionTime", auctionTime);
			
			Client client = Client.create();
			WebResource webResource = client.resource("https://localhost:8445/Bid_Microservice/deleteSelectedItem/deleteItem");
			ClientResponse restResponse = webResource
					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);
						
			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("MyItems.jsp");
		rd.forward(request, response);
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	}

}
