package com.example.jms;
import javax.jms.*;
import javax.naming.*;
public class FirstTopic {

	public static void main(String[] args) throws Exception {
		
		 InitialContext initialConnection= new InitialContext();
		Topic topic=(Topic) initialConnection.lookup("topic/myTopic");
		ConnectionFactory cf=(ConnectionFactory) initialConnection.lookup("ConnectionFactory");
		Connection connection= cf.createConnection();
		Session session= connection.createSession();
		MessageProducer producer = session.createProducer(topic);
		
		MessageConsumer consumer1 =session.createConsumer(topic);
		MessageConsumer consumer2 =session.createConsumer(topic);
		MessageConsumer consumer3 =session.createConsumer(topic);
		TextMessage message = session.createTextMessage("All the power");
		
		producer.send(message);
		connection.start();
		TextMessage text1 =(TextMessage)consumer1.receive(5000);
		System.out.println("received message1 "+ text1.getText());
		TextMessage text2 = (TextMessage)consumer2.receive(5000);
		System.out.println("received message2 "+ text2.getText());
		TextMessage text3 = (TextMessage)consumer3.receive(5000);
		System.out.println("received message3 "+ text3.getText());
		connection.close();
	}

}
