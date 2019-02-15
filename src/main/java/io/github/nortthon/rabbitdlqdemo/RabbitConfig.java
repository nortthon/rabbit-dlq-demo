package io.github.nortthon.rabbitdlqdemo;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    public static final String EXCHANGE_NAME = "exchange.name";
    public static final String ROUTING_KEY_NAME = "routing.key.name";
    public static final String QUEUE_NAME = "queue.name";
    public static final String DEAD_LETTER_ROUTING_KEY_NAME = "dead-letter.routing.key.name";
    public static final String DEAD_LETTER_QUEUE_NAME = "dead-letter.queue.name";

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue deadLetterQueue() {
        return QueueBuilder.durable(DEAD_LETTER_QUEUE_NAME).build();

    }

    @Bean
    public Queue incomingQueue() {
        return QueueBuilder.durable(QUEUE_NAME)
                .withArgument("x-dead-letter-exchange", EXCHANGE_NAME)
                .withArgument("x-dead-letter-routing-key", DEAD_LETTER_ROUTING_KEY_NAME)
                .build();
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(incomingQueue()).to(exchange()).with(ROUTING_KEY_NAME);
    }

    @Bean
    public Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(exchange()).with(DEAD_LETTER_ROUTING_KEY_NAME);
    }
}
