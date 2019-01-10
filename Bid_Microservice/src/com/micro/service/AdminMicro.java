package com.micro.service;

import com.micro.dao.*;
import java.text.*;
import java.sql.ResultSet;
import java.sql.*;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
import java.io.InputStream;
import java.io.ByteArrayInputStream;

@Path("/adminMicroService")
public class AdminMicro {
	@Path("/changeTime")
	@POST
	@Consumes("application/x-www-form-urlencoded")
	@Produces(MediaType.TEXT_PLAIN)

	public Response checkUsername(MultivaluedMap<String, String> formParam) {

		String date = String.valueOf(formParam.getFirst("date"));
		String startTime = String.valueOf(formParam.getFirst("startTime"));
		String endTime = String.valueOf(formParam.getFirst("endTime"));

		String start [] = startTime.split(":");
		String end [] = endTime.split(":");
		int h_s = Integer.parseInt(start[0]);
		int h_e = Integer.parseInt(end[0]);
		int m_s = Integer.parseInt(start[1]);
		int m_e = Integer.parseInt(end[1]);
		int duration = ((h_e-h_s)*60)+m_e-m_s;
		int slot = duration/15;
		String [] slots =new String[slot];
		slots[0] = startTime;
		for(int i=1;i<slot;i++){
			slots[i]=getSlot(slots[i-1]);
		}

		StringBuilder sb = new StringBuilder("");

		for(String s : slots){
			sb.append(s + ",");
		}
		String slotsDate = sb.toString();
		try {
			DBQuery.insertResult("Delete from auctionDuration where auctionDate = '" + date + "'");
			DBQuery.insertResult("insert into auctionDuration values ('" + date + "','" + startTime + "','" + endTime + "','" + slotsDate + "')");

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return Response.ok().entity(String.valueOf(true)).build();
	}

	public String getSlot(String s){
		StringBuilder sb=new StringBuilder();
		String t []=s.split(":");
		int h=Integer.parseInt(t[0]);
		int m=Integer.parseInt(t[1]);
		m+=15;
		if(m>=60){
			m=m%60;
			h++;
		}
		if(h<10){
			sb.append('0');
		}
		sb.append(h);
		sb.append(":");
		if(m<10){
			sb.append('0');
		}
		sb.append(m);
		sb.append(":00");

		return sb.toString();
	}
}
