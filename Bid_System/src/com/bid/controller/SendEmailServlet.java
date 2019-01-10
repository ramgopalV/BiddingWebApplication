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
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

import javax.ws.rs.core.MultivaluedMap;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/SendEmailServlet")
public class SendEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SendEmailServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		String to = request.getParameter("email");
		String from = "varunmehrotra.mehra@gmail.com";
		String host = "localhost";
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.put("mail.smtp.port", "25");
		Session s = Session.getDefaultInstance(properties);
		response.setContentType("text/html");
	     PrintWriter out = response.getWriter();
	      
		try {
			MimeMessage message = new MimeMessage(s);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("Reset Password -- Bid System Application");
			message.setText("Please use the link to reset password :- http://localhost:8080/Bid_System/resetPassword.jsp");
			
			Transport.send(message);
	         String title = "Reset Password";
	         String res = "Sent message successfully....";
	         String docType =
	            "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
	         
	         out.println(docType +
	            "<html>\n" +
	               "<head><title>" + title + "</title></head>\n" +
	               "<body bgcolor = \"#f0f0f0\">\n" +
	                  "<h1 align = \"center\">" + title + "</h1>\n" +
	                  "<p align = \"center\">" + res + "</p>\n" +
	               "</body>" +
	            "</html>"
	         );
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("forgotPassword.jsp");
		rd.forward(request, response);
	}

}
