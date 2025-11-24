package com.example.jms;

import jakarta.jms.*;
import org.apache.activemq.artemis.jms.client.ActiveMQConnectionFactory;

public class TopicSubscriber {
    public static void main(String[] args) {
        String brokerURL = "tcp://localhost:61616";
        String user = "admin";
        String pass = "admin";
        String subscriberName = args.length > 0 ? args[0] : "defaultSub";

        try (ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(brokerURL);
             JMSContext context = cf.createContext(user, pass, JMSContext.AUTO_ACKNOWLEDGE)) {

            context.setClientID(subscriberName);
            Topic topic = context.createTopic("newsTopic");

            JMSConsumer durableConsumer = context.createDurableConsumer(topic, subscriberName);
            System.out.println("🟡 " + subscriberName + " en attente de messages...");

            String msg = durableConsumer.receiveBody(String.class, 5000);
            if (msg != null) {
                System.out.println("📩 " + subscriberName + " a reçu : " + msg);
            } else {
                System.out.println("⚙️ Aucun message reçu (peut-être envoyé avant l'abonnement)");
            }

            durableConsumer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
