package org.romanzhula.activemqproducer.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.romanzhula.activemqproducer.exceptions.InvalidMessageException;
import org.romanzhula.activemqproducer.exceptions.InvalidMessageTypeException;
import org.romanzhula.activemqproducer.models.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageProducerService {

    @Value("${jms.topic}")
    String topicName;

    private final JmsTemplate jmsTemplate;
    private final ObjectMapper objectMapper;

    public MessageProducerService(
            JmsTemplate jmsTemplate,
            ObjectMapper objectMapper
    ) {
        this.jmsTemplate = jmsTemplate;
        this.objectMapper = objectMapper;
    }


    public void sendMessageToQueueWithType(
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

        jmsTemplate.convertAndSend(topicName, jsonMessage, message -> {
            message.setStringProperty("type", type);
            return message;
        });

        System.out.println("Sent the message: " + customMessage.getMessage() + " with type: " + type);
    }

}
