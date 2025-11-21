package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ConsumerApp {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        String user = "admin"; // identifiant du docker-compose
        String pass = "admin";

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(user, pass, JMSContext.AUTO_ACKNOWLEDGE)) {

            Queue queue = context.createQueue("demoQueue");
            JMSConsumer consumer = context.createConsumer(queue);

            System.out.println("🟡 En attente de messages...");
            String msg = consumer.receiveBody(String.class);
            System.out.println("📩 Message reçu : " + msg);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
