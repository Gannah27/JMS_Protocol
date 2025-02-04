package com.example.jms;

import javax.naming.InitialContext;
import javax.jms.*;

public class MessagePriority {

	public static void main(String[] args) throws Exception {
		
		InitialContext initialcontext= new InitialContext();
		Queue queue = (Queue)initialcontext.lookup("queue/myQueue");
		
		
	}

}
