package org.romanzhula.activemqproducer.exceptions;

public class QueueCreationException extends RuntimeException {

    public QueueCreationException(String message) {
        super(message);
    }

    public QueueCreationException(String message, Throwable cause) {
        super(message, cause);
    }

}
