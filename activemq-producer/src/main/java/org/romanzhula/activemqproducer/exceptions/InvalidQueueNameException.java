package org.romanzhula.activemqproducer.exceptions;

public class InvalidQueueNameException extends RuntimeException {

    public InvalidQueueNameException(String message) {
        super(message);
    }

}
