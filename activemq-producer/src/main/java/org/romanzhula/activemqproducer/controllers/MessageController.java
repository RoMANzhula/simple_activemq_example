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

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(
            @RequestBody Message message,
            @RequestParam String type
    ) throws JsonProcessingException {
        messageProducerService.sendMessage(message, type);

        return ResponseEntity.ok("Message sent successfully: " + message.getMessage());
    }

}
