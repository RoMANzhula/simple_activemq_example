package org.romanzhula.activemqproducer.exception_handlers;

import org.romanzhula.activemqproducer.exceptions.InvalidMessageException;
import org.romanzhula.activemqproducer.exceptions.InvalidMessageTypeException;
import org.romanzhula.activemqproducer.exceptions.QueueCreationException;
import org.romanzhula.activemqproducer.exceptions.TopicCreationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
        return new ResponseEntity<>("An Error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(InvalidMessageException.class)
    public ResponseEntity<String> handleInvalidMessageException(InvalidMessageException e) {
        return new ResponseEntity<>("The message is incorrect: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidMessageTypeException.class)
    public ResponseEntity<String> handleInvalidMessageTypeException(InvalidMessageTypeException e) {
        return new ResponseEntity<>("Invalid type of message: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(QueueCreationException.class)
    public ResponseEntity<String> handleQueueCreationException(QueueCreationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

    @ExceptionHandler(TopicCreationException.class)
    public ResponseEntity<String> handleTopicCreationException(TopicCreationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
    }

}
