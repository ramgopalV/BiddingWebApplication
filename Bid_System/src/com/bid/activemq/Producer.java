package com.bid.activemq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.json.JSONArray;
import org.json.JSONObject;
import com.sun.jersey.core.util.*;
import javax.ws.rs.core.MultivaluedMap;
import org.apache.activemq.ActiveMQConnectionFactory;
import javax.jms.*;
import java.util.Random;
import org.json.*;

public class Producer{

	public static void sentToQueue(String profileArray) throws JMSException {

		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		Connection connection = null;
		Session session = null;
		
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue("Bid App");
			MessageProducer producer = session.createProducer(destination);

			TextMessage message = session.createTextMessage(profileArray);
			
			producer.send(message);
		} 
		catch (JMSException e) {
			e.printStackTrace();
		}
		finally {
			session.close();
			connection.close();
		}
	}
}
