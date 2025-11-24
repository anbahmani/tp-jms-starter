package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class ProducerApp {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        String user = "admin";
        String pass = "admin";

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(user, pass, JMSContext.SESSION_TRANSACTED)) {

            Queue queue = context.createQueue("transactionQueue");
            for (int i = 1; i <= 3; i++) {
                String message = "TX Message #" + i;
                context.createProducer().send(queue, message);
                System.out.println("🟢 Message ajouté à la transaction : " + message);
            }

            System.out.println("✅ Commit de la transaction...");
            context.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
