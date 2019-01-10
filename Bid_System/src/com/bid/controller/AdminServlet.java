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
@WebServlet("/AdminServlet")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");

		String date = request.getParameter("available-date");
		String startTime = request.getParameter("start-time");
		String endTime = request.getParameter("end-time");
		
		System.out.println(date + " " + startTime + " " + endTime);
		try {

			Client client = Client.create();
			WebResource webResource = client.resource("https://localhost:8444/Bid_WebService/adminWebService/changeTime");
			MultivaluedMap formData = new MultivaluedMapImpl();
			formData.add("date", date);
			formData.add("startTime", startTime);
			formData.add("endTime", endTime);

			ClientResponse restResponse = webResource
					.type(MediaType.APPLICATION_FORM_URLENCODED_TYPE)
					.post(ClientResponse.class, formData);

			if (restResponse.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : " + restResponse.getStatus());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("admin.jsp");
		rd.forward(request, response);
	}

}
