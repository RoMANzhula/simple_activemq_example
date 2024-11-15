package org.romanzhula.activemqconsumera.configurations;

import jakarta.jms.ConnectionFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;

@EnableJms
@Configuration
public class JmsConfig {

    @Value("${spring.activemq.broker-url}")
    private String BROKER_URL;

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory(BROKER_URL);
    }

    @Bean
    public DefaultJmsListenerContainerFactory queueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setPubSubDomain(false); // mode for queues
        return factory;
    }

}