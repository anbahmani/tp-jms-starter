package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class TopicPublisher {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        String user = "admin";
        String pass = "admin";

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(user, pass, JMSContext.AUTO_ACKNOWLEDGE)) {

            Topic topic = context.createTopic("newsTopic");
            context.createProducer().send(topic, "📰 Nouvelle info JMS !");
            System.out.println("✅ Message publié sur le topic !");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
