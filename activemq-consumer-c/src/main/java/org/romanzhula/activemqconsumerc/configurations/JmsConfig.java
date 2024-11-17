package org.romanzhula.activemqconsumerc.configurations;

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

    @Value("${spring.jms.client-id}")
    private String CLIENT_ID;

    @Bean
    public ConnectionFactory queueConnectionFactory() {
        return new ActiveMQConnectionFactory(BROKER_URL);
    }

    @Bean
    public ConnectionFactory topicConnectionFactory1() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connectionFactory.setClientID(CLIENT_ID + "-topic1");
        return connectionFactory;
    }

    @Bean
    public ConnectionFactory topicConnectionFactory2() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(BROKER_URL);
        connectionFactory.setClientID(CLIENT_ID + "-topic2");
        return connectionFactory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory queueListenerFactory() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(queueConnectionFactory());
        factory.setPubSubDomain(false); // mode for queues
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory topicListenerFactory1() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(topicConnectionFactory1());
        factory.setPubSubDomain(true); // mode for topics
        factory.setSubscriptionDurable(true); // Durable subscription
        return factory;
    }

    @Bean
    public DefaultJmsListenerContainerFactory topicListenerFactory2() {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(topicConnectionFactory2());
        factory.setPubSubDomain(true); // mode for topics
        factory.setSubscriptionDurable(true); // Durable subscription
        return factory;
    }

}
