package org.romanzhula.activemqproducer.helpers;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import org.romanzhula.activemqproducer.models.Message;

import java.io.IOException;

public class MessageDeserializer extends JsonDeserializer<Message> {

    @Override
    public Message deserialize(
            JsonParser jsonParser,
            DeserializationContext deserializationContext
    ) throws IOException, JacksonException {

        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String message = node.get("message").asText();

        return new Message(message);
    }
    
}
