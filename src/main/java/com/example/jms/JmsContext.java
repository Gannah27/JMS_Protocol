package com.example.jms;
import javax.jms.Queue;

import javax.jms.JMSContext;
import javax.naming.*;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class JmsContext {

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		Queue queue = (Queue)context.lookup("queue/myQueue");
		
		ActiveMQConnectionFactory cf =new ActiveMQConnectionFactory();
		JMSContext jmscontext = cf.createContext();
		jmscontext.createProducer().send(queue, "heeyyyyy");
		
		String messagereceived =jmscontext.createConsumer(queue).receiveBody(String.class);
		 System.out.println("Message received: " + messagereceived);
	}

}
