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
            context.createProducer().send(queue, "Hello JMS with authentication!");
            System.out.println("✅ Message envoyé sur demoQueue !");
        } catch (JMSRuntimeException e) {
            e.printStackTrace();
        }
    }
}
