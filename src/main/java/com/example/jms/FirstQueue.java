package com.example.jms;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.jms.*;

public class FirstQueue {

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
			TextMessage message=session.createTextMessage("Iam the creator ");
			producer.send(message);
			System.out.println("Message Sent: " + message.getText());
			
			MessageConsumer consumer = session.createConsumer(queue);
			connection.start();
			TextMessage msg = (TextMessage)consumer.receive(5000);
			System.out.println("Message Received: " + msg.getText());
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
