package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ProducerApp {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        String user = "admin";
        String pass = "admin";

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(user, pass, JMSContext.AUTO_ACKNOWLEDGE)) {

            Queue queue = context.createQueue("demoQueue");
            for (int i = 1; i <= 5; i++) {
                String message = "{\"orderId\": " + i + ", \"status\": \"CREATED\"}";
                context.createProducer().send(queue, message);
                System.out.println("✅ Message envoyé : " + message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
