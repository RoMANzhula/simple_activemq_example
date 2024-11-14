package org.romanzhula.activemqproducer.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.romanzhula.activemqproducer.helpers.MessageDeserializer;

import java.io.Serializable;


@JsonDeserialize(using = MessageDeserializer.class)
public class Message implements Serializable {

    private static final long serialVersionUID = 1L;

    private final String message;


    public Message(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}
