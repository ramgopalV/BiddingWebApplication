package com.bid.controller;

import org.json.*;
import com.bid.activemq.*;
import com.bid.bean.*;
import java.io.InputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.*;
import java.text.*;
import java.util.*;
import java.io.*;
import javax.servlet.http.Part;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/postBidServlet")
public class PostBidServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PostBidServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		
		String itemName = request.getParameter("text_itemTitle");
		String newBidValue = request.getParameter("text_bidValue");
		
		String username = String.valueOf(session.getAttribute("USER"));
		JSONArray profileArray = new JSONArray();
		JSONObject obj = new JSONObject();
		
		try {
			obj.put("itemName", itemName);
			obj.put("username", username);
			obj.put("newBidValue", newBidValue);
			profileArray.put(obj);
			
			Producer.sentToQueue(obj.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("Bid.jsp");
		rd.forward(request, response);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("Bid.jsp");
		rd.forward(request, response);
	}

}
