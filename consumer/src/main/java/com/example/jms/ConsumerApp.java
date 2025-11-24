package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ConsumerApp {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        String user = "admin";
        String pass = "admin";

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(user, pass, JMSContext.AUTO_ACKNOWLEDGE)) {

            Queue queue = context.createQueue("demoQueue");
            JMSConsumer consumer = context.createConsumer(queue);

            System.out.println("🟡 En attente de messages...");
            while (true) {
                String msg = consumer.receiveBody(String.class, 3000);
                if (msg == null) break;
                System.out.println("📩 Reçu : " + msg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
