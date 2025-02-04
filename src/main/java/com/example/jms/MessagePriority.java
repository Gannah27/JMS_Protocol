package com.example.jms;

import javax.naming.InitialContext;

import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

import javax.jms.*;

public class MessagePriority {

	public static void main(String[] args) throws Exception {
		
		InitialContext initialcontext= new InitialContext();
		Queue queue = (Queue)initialcontext.lookup("queue/myQueue");
		
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
		JMSContext jmscontext =cf.createContext();
		JMSProducer producer =jmscontext.createProducer();
		String[] messages= new String[3];
		messages[0]="Message one";
		messages[1]="Message two";
		messages[2]="Message three";
		producer.setPriority(3);
		producer.send(queue, messages[0]);
		
		producer.setPriority(1);
		producer.send(queue, messages[1]);
		
		producer.setPriority(9);
		producer.send(queue, messages[2]);
		
		JMSConsumer consumer = jmscontext.createConsumer(queue);
		
	
		for(int i=0;i<3;i++) {
			String msg=consumer.receiveBody(String.class);
			System.out.println("Message recived "+ msg);
		}
	}

}
