package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ConsumerApp {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        try (ConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(Session.AUTO_ACKNOWLEDGE)) {

            Queue queue = context.createQueue("demoQueue");
            JMSConsumer consumer = context.createConsumer(queue);
            System.out.println("🟡 En attente de messages...");
            String msg = consumer.receiveBody(String.class);
            System.out.println("📩 Message reçu : " + msg);
        }
    }
}
