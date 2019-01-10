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

import org.json.JSONArray;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import java.io.PrintWriter;

/**
 * Servlet implementation class FetchProfileServlet
 */
@WebServlet("/FetchProfileServlet")
public class FetchProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FetchProfileServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		Boolean status = false;
		JSONArray profileArray = null;
		PrintWriter out = null;
		
		HttpSession session = request.getSession();
		
		try {
			String username = String.valueOf(session.getAttribute("USER"));
			Client client = Client.create();
			WebResource webResource = client.resource("https://localhost:8444/Bid_WebService/fetchProfile/userProfile/" + username);
			ClientResponse restResponse = webResource
					.type("application/json")
					.get(ClientResponse.class);
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
