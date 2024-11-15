package org.romanzhula.activemqproducer.exceptions;

public class TopicCreationException extends RuntimeException {

    public TopicCreationException(String message) {
        super(message);
    }

    public TopicCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
