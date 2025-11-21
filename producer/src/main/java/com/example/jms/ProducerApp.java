package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ProducerApp {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        try (ConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext()) {

            Queue queue = context.createQueue("demoQueue");
            context.createProducer().send(queue, "Hello JMS from Producer!");
            System.out.println("✅ Message envoyé sur demoQueue !");
        }
    }
}
