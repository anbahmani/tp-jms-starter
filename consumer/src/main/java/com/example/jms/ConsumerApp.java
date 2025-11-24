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

            Queue queue = context.createQueue("errorQueue");
            JMSConsumer consumer = context.createConsumer(queue);

            System.out.println("⚠️ Simulation d'erreurs pour la DLQ...");
            while (true) {
                String msg = consumer.receiveBody(String.class, 3000);
                if (msg == null) break;

                System.out.println("📩 Reçu : " + msg);
                if (msg.contains("ERROR")) {
                    System.out.println("💥 Erreur simulée, message non traité !");
                    throw new RuntimeException("Erreur de traitement simulée");
                } else {
                    System.out.println("✅ Message traité avec succès");
                }
            }
        } catch (Exception e) {
            System.err.println("🚨 Exception capturée : " + e.getMessage());
        }
    }
}
