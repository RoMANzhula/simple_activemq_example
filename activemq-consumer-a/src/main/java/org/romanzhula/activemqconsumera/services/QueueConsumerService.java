package org.romanzhula.activemqconsumera.services;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class QueueConsumerService {

    @JmsListener(destination = "firstQueue", containerFactory = "queueListenerFactory", selector = "type = 'ERROR'")
    public void consumeMessageFromQueueWithTypeError(String message) {
        System.out.println("Received message from queue: " + message);
    }

    @JmsListener(destination = "firstQueue", containerFactory = "queueListenerFactory", selector = "type = 'INFO'")
    public void consumeMessageFromQueueWithTypeInfo(String message) {
        System.out.println("Received message from queue: " + message);
    }

    //receive all message, ignore any types as selector
//    @JmsListener(destination = "firstQueue", containerFactory = "queueListenerFactory")
//    public void consumeMessageFromQueueForAllMessages(String message) {
//        System.out.println("Received message from queue: " + message);
//    }

}
