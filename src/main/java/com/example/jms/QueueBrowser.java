package com.example.jms;

import javax.naming.InitialContext;

import javax.naming.NamingException;

import java.util.Enumeration;

import javax.jms.*;

public class QueueBrowser {

	public static void main(String[] args)  {
		InitialContext initialContext= null;
		Connection connection=null;
		try {
			initialContext = new InitialContext();
			ConnectionFactory cf= (ConnectionFactory) initialContext.lookup("ConnectionFactory");
			connection =cf.createConnection();
			Session session = connection.createSession();
			Queue queue= (Queue)initialContext.lookup("queue/myQueue");
			MessageProducer producer=  session.createProducer(queue);
			TextMessage message=session.createTextMessage("Iam the creatorrrrr ");
			TextMessage message2=session.createTextMessage("Iam the creator 2 ");
			producer.send(message);
			System.out.println("Message Sent: " + message.getText());
			producer.send(message2);
			System.out.println("Message Sent: " + message2.getText());
			javax.jms.QueueBrowser browser = session.createBrowser(queue);
			Enumeration enumeration= browser.getEnumeration();
			while(enumeration.hasMoreElements()) {
				TextMessage m = (TextMessage)enumeration.nextElement();
				System.out.println("message in enum "+ m.getText());
			}
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start();
			TextMessage msg = (TextMessage)consumer.receive(5000);
			System.out.println("Message Received1: " + msg.getText());
			msg = (TextMessage)consumer.receive(5000);
			System.out.println("Message Received2: " + msg.getText());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(initialContext!=null) {
				try {
					initialContext.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}			}
		if(connection!=null) {
			try {
				connection.close();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		}
		
		

	}

}
