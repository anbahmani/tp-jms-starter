package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ConsumerApp {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        String user = "admin";
        String pass = "admin";

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(user, pass, JMSContext.CLIENT_ACKNOWLEDGE)) {

            Queue queue = context.createQueue("transactionQueue");
            JMSConsumer consumer = context.createConsumer(queue);

            System.out.println("🟡 Lecture manuelle avec ACK...");
            while (true) {
                Message msg = consumer.receive(3000);
                if (msg == null) break;
                System.out.println("📩 Reçu : " + msg.getBody(String.class));
                msg.acknowledge();
                System.out.println("👍 Message acquitté manuellement");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
