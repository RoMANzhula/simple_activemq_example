package org.romanzhula.activemqproducer.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.romanzhula.activemqproducer.models.Message;
import org.romanzhula.activemqproducer.services.MessageProducerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class MessageController {

    private final MessageProducerService messageProducerService;

    public MessageController(MessageProducerService messageProducerService) {
        this.messageProducerService = messageProducerService;
    }

    @PostMapping("/sendMessageToQueueWithTypeTwoParams")
    public ResponseEntity<?> sendMessageToQueueWithTypeTwoParams(
            @RequestBody Message message,
            @RequestParam String type
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToQueueWithTypeTwoParams(message, type);

        return ResponseEntity.ok("Message sent successfully: " + message.getMessage());
    }

    @PostMapping("/sendToQueueWithType")
    public ResponseEntity<?> sendMessageToQueueWithType(
            @RequestBody Message message,
            @RequestParam String type
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToDestinationWithType(message, type, true);

        return ResponseEntity.ok(
                "Message sent successfully to queue with type: " +
                type +
                ", " +
                message.getMessage()
        );
    }

    @PostMapping("/sendToTopicWithType")
    public ResponseEntity<?> sendMessageToTopicWithType(
            @RequestBody Message message,
            @RequestParam String type
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToDestinationWithType(message, type, false);

        return ResponseEntity.ok(
                "Message sent successfully to topic with type: " +
                type +
                ", " +
                message.getMessage()
        );
    }

    @PostMapping("/sendWithTypeTurnOnWhere")
    public ResponseEntity<?> sendMessageWithType(
            @RequestBody Message message,
            @RequestParam String type,
            @RequestParam boolean sendToQueue
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToDestinationWithType(message, type, sendToQueue);
        String destination = sendToQueue ? "queue" : "topic";

        return ResponseEntity.ok(
                "Message sent successfully to " +
                destination +
                " with type: " +
                type +
                ", " +
                message.getMessage()
        );
    }

    @PostMapping("/sendToQueueWithoutType")
    public ResponseEntity<?> sendMessageToQueue(
            @RequestBody Message message
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToDestination(message, true);

        return ResponseEntity.ok("Message sent successfully to queue: " + message.getMessage());
    }

    @PostMapping("/sendToTopicWithoutType")
    public ResponseEntity<?> sendMessageToTopic(
            @RequestBody Message message
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToDestination(message, false);

        return ResponseEntity.ok("Message sent successfully to topic: " + message.getMessage());
    }

    @PostMapping("/sendTurnOnWhereWithoutType")
    public ResponseEntity<?> sendMessage(
            @RequestBody Message message,
            @RequestParam boolean sendToQueue
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToDestination(message, sendToQueue);
        String destination = sendToQueue ? "queue" : "topic";

        return ResponseEntity.ok("Message sent successfully to " + destination + ": " + message.getMessage());
    }


    @PostMapping("/createQueue")
    public ResponseEntity<String> createQueue(
            @RequestParam String queueName
    ) {
        messageProducerService.createQueue(queueName);
        return ResponseEntity.ok("Queue created: " + queueName);
    }

    @PostMapping("/createTopic")
    public ResponseEntity<String> createTopic(
            @RequestParam String topicName
    ) {
        messageProducerService.createTopic(topicName);
        return ResponseEntity.ok("Topic created: " + topicName);
    }

    @PostMapping("/sendToQueue")
    public ResponseEntity<?> sendMessageToQueue(
            @RequestBody Message message,
            @RequestParam String queueName
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToQueue(message, queueName);

        return ResponseEntity.ok("Message sent to queue " + queueName + " successfully: " + message.getMessage());
    }

    @PostMapping("/sendToTopic")
    public ResponseEntity<?> sendMessageToTopic(
            @RequestBody Message message,
            @RequestParam String topicName
    ) throws JsonProcessingException {

        messageProducerService.sendMessageToTopic(message, topicName);

        return ResponseEntity.ok("Message sent to topic " + topicName + " successfully: " + message.getMessage());
    }

}
