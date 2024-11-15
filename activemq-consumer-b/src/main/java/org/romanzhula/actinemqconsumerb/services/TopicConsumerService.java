package org.romanzhula.actinemqconsumerb.services;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

@Service
public class TopicConsumerService {

    //receiver for all consumers which subscribed to current topic
//    @JmsListener(destination = "firstTopic", containerFactory = "topicListenerFactory", subscription = "subscriptionName") //with subscription = "subscriptionName" - for all consumers
//    public void consumeMessageFromTopicToAllConsumers(String message) {
//        System.out.println("Received message from topic: " + message);
//    }

    //receive message from topic "myTopic"(producer service method for mapping sendMessageToTopicWithType()) with type
    @JmsListener(destination = "myTopic", containerFactory = "topicListenerFactory", selector = "type = 'ERROR'")
    public void consumeMessageFromTopicWithTypeError(String message) {
        System.out.println("Received message from topic with type ERROR: " + message);
    }

    @JmsListener(destination = "myTopic", containerFactory = "topicListenerFactory", selector = "type = 'INFO'")
    public void consumeMessageFromTopicWithTypeInfo(String message) {
        System.out.println("Received message from topic with type INFO: " + message);
    }

}
