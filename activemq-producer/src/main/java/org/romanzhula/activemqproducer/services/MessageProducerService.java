package org.romanzhula.activemqproducer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.romanzhula.activemqproducer.exceptions.*;
import org.romanzhula.activemqproducer.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {

    @Value("${jms.topic.name}")
    String topicName;

    @Value("${jms.queue.name}")
    String queueName;


    private final JmsTemplate queueJmsTemplate;
    private final JmsTemplate topicJmsTemplate;
    private final ObjectMapper objectMapper;

    public MessageProducerService(
            JmsTemplate queueJmsTemplate,
            JmsTemplate topicJmsTemplate,
            ObjectMapper objectMapper
    ) {
        this.queueJmsTemplate = queueJmsTemplate;
        this.topicJmsTemplate = topicJmsTemplate;
        this.objectMapper = objectMapper;
    }


    public void sendMessageToQueueWithTypeTwoParams(
            Message customMessage,
            String type
    ) throws JsonProcessingException {

        if (customMessage.getMessage().isBlank()) {
            System.out.println("Message cannot be empty.");
            throw new InvalidMessageException("Message cannot be empty!");
        }

        if (type.isBlank()) {
            System.out.println("Type of massage cannot be empty.");
            throw new InvalidMessageTypeException("Type of message cannot be empty!");
        }

        String jsonMessage = objectMapper.writeValueAsString(customMessage);

        queueJmsTemplate.convertAndSend(queueName, jsonMessage, message -> {
            message.setStringProperty("type", type);
            return message;
        });

        System.out.println("Sent the message: " + customMessage.getMessage() + " with type: " + type);
    }


    public void sendMessageToDestinationWithType(
            Message customMessage,
            String type,
            boolean sendToQueue
    ) throws JsonProcessingException {

        if (customMessage.getMessage().isBlank()) {
            throw new InvalidMessageException("Message cannot be empty!");
        }

        if (type.isBlank()) {
            throw new InvalidMessageTypeException("Type of message cannot be empty!");
        }

        String jsonMessage = objectMapper.writeValueAsString(customMessage);

        JmsTemplate jmsTemplate = sendToQueue ? queueJmsTemplate : topicJmsTemplate;
        jmsTemplate.convertAndSend(sendToQueue ? "myQueue" : "myTopic", jsonMessage, message -> {
            message.setStringProperty("type", type);
            return message;
        });

        System.out.println(
                "Sent message: " + customMessage.getMessage() + " with type: " +
                type + " to " + (sendToQueue ? "queue" : "topic")
        );
    }


    public void sendMessageToDestination(
            Message customMessage,
            boolean sendToQueue
    ) throws JsonProcessingException {

        if (customMessage.getMessage().isBlank()) {
            throw new InvalidMessageException("Message cannot be empty!");
        }

        String jsonMessage = objectMapper.writeValueAsString(customMessage);

        JmsTemplate jmsTemplate = sendToQueue ? queueJmsTemplate : topicJmsTemplate;
        jmsTemplate.convertAndSend(sendToQueue ? "myQueue" : "myTopic", jsonMessage);

        System.out.println("Sent message: " + customMessage.getMessage() + " to " + (sendToQueue ? "queue" : "topic"));
    }


    public void createQueue(String queueName) {

        if (queueName.isBlank()) {
            throw new InvalidQueueNameException("Queue name cannot be empty!");
        }

        try {
            queueJmsTemplate.send(queueName, session -> session.createTextMessage("Queue " + queueName + " created"));
            System.out.println("Queue created: " + queueName);
        } catch (Exception e) {
            System.out.println("Error creating queue: " + e.getMessage());
            throw new QueueCreationException("Failed to create queue: " + queueName, e);
        }
    }


    public void createTopic(String topicName) {

        if (topicName.isBlank()) {
            throw new InvalidTopicNameException("Topic name cannot be empty!");
        }

        try {
            topicJmsTemplate.send(topicName, session -> session.createTextMessage("Topic " + topicName + " created"));
            System.out.println("Topic created: " + topicName);
        } catch (Exception e) {
            System.out.println("Error creating topic: " + e.getMessage());
            throw new TopicCreationException("Failed to create topic: " + topicName, e);
        }
    }


    public void sendMessageToQueue(
            Message customMessage,
            String queueName
    ) throws JsonProcessingException {

        if (customMessage.getMessage().isBlank()) {
            throw new InvalidMessageException("Message cannot be empty!");
        }

        if (queueName.isBlank()) {
            throw new InvalidQueueNameException("Queue name cannot be empty!");
        }

        String jsonMessage = objectMapper.writeValueAsString(customMessage);
        queueJmsTemplate.convertAndSend(queueName, jsonMessage);
        System.out.println("Sent message to queue: " + customMessage.getMessage() + " in queue: " + queueName);
    }


    public void sendMessageToTopic(
            Message customMessage,
            String topicName
    ) throws JsonProcessingException {

        if (customMessage.getMessage().isBlank()) {
            throw new InvalidMessageException("Message cannot be empty!");
        }

        if (topicName.isBlank()) {
            throw new InvalidTopicNameException("Topic name cannot be empty!");
        }

        String jsonMessage = objectMapper.writeValueAsString(customMessage);
        topicJmsTemplate.convertAndSend(topicName, jsonMessage);
        System.out.println("Sent message to topic: " + customMessage.getMessage() + " in topic: " + topicName);
    }

}
