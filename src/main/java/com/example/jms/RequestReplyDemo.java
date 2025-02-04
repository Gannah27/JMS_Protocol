package com.example.jms;
import javax.jms.Queue;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSProducer;
import javax.naming.*;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class RequestReplyDemo {

	public static void main(String[] args) throws Exception {
		InitialContext context = new InitialContext();
		Queue queue = (Queue)context.lookup("queue/requestQueue");
		
		ActiveMQConnectionFactory cf =new ActiveMQConnectionFactory();
		JMSContext jmscontext = cf.createContext();
		JMSProducer producer =jmscontext.createProducer();
		producer.send(queue, "heeyyyyy man");
		
		JMSConsumer consumer = jmscontext.createConsumer(queue);
		String messagereceived =consumer.receiveBody(String.class);
		 System.out.println("Message received: " + messagereceived);
		 
		 JMSProducer repproducer1 = jmscontext.createProducer();
		 Queue queue1 = (Queue)context.lookup("queue/replyQueue");
		 repproducer1.send(queue1, "Bye man");
		 JMSConsumer consumer1 = jmscontext.createConsumer(queue1);
		 String messagereceived1 =consumer1.receiveBody(String.class);
		 System.out.println("Message received: " + messagereceived1);
	}

}
