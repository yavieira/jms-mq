package br.com.activemq;

import java.util.Scanner;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.naming.InitialContext;

public class ActiveMQTest {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		
		InitialContext context = new InitialContext();
		ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection con = factory.createConnection();
		con.start();
		
		Session session = con.createSession(false, Session.AUTO_ACKNOWLEDGE); //transação, autoconfirmação 
		Destination fila = (Destination) context.lookup("financeiro"); //lookup na fila
		MessageConsumer consumer = session.createConsumer(fila); // cria consumidor
		Message message = consumer.receive();
		
		System.out.println("Recebendo mensagem: " + message);
		
		new Scanner(System.in).nextLine();
		
		session.close();
		con.close();
		context.close();
	}
}
