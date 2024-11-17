package org.romanzhula.activemqconsumerc.services;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class CombinedConsumerService {

        // receivers for messages from common queues and topics

    @JmsListener(destination = "myQueue", containerFactory = "queueListenerFactory")
    public void consumeMessageFromQueue(String message) {
        System.out.println("Received message from queue myQueue without type: " + message);
    }

    @JmsListener(destination = "myTopic", containerFactory = "topicListenerFactory1", subscription = "subscriptionName", selector = "type = 'UNKNOWN'")
    public void consumeMessageFromTopic(String message) {
        System.out.println("Received message from topic myTopic with type UNKNOWN: " + message);
    }

    @JmsListener(destination = "myTopic", containerFactory = "topicListenerFactory2", subscription = "subscriptionName")
    public void consumeMessageFromTopicWithoutType(String message) {
        System.out.println("Received message from topic myTopic without type or any types: " + message);
    }

}
