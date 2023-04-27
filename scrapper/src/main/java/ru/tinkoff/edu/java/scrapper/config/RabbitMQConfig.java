package ru.tinkoff.edu.java.scrapper.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    private final String exchangeName;
    private final String queueName;
    private final String routingKey;

    private final static String DLX_SUFFIX = ".dlq";

    public RabbitMQConfig(ApplicationConfig config) {
        this.exchangeName = config.getRabbitQueue().getExchangeName();
        this.queueName = config.getRabbitQueue().getQueueName();
        this.routingKey = config.getRabbitQueue().getRoutingKey();
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchangeName, true, false);
    }

    @Bean
    public Queue queue() {
        return QueueBuilder
                .durable(queueName)
                .withArgument("x-dead-letter-exchange", exchangeName + DLX_SUFFIX)
                .withArgument("x-dead-letter-routing-key", routingKey + DLX_SUFFIX)
                .build();
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);
    }
}
