package org.romanzhula.activemqproducer.exceptions;

public class InvalidMessageException extends RuntimeException {

    public InvalidMessageException(String message) {
        super(message);
    }
}
