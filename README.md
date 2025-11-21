# TP JMS — Jakarta EE & Apache Artemis

## Objectifs
- Découvrir la messagerie asynchrone JMS.
- Utiliser Apache Artemis via Docker.
- Envoyer et recevoir des messages avec Jakarta JMS.

## Démarrage du broker
```bash
docker compose up -d
```
Console web : http://localhost:8161  (admin / admin)

## Lancer le producteur
```bash
mvn -pl producer exec:java -Dexec.mainClass=com.example.jms.ProducerApp
```

## Lancer le consommateur
```bash
mvn -pl consumer exec:java -Dexec.mainClass=com.example.jms.ConsumerApp
```
