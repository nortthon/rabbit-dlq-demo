package io.github.nortthon.rabbitdlqdemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.naming.InsufficientResourcesException;

@Slf4j
@Component
public class Consumer {

    @RabbitListener(queues = RabbitConfig.QUEUE_NAME)
    public void process(@Payload final String message) throws InsufficientResourcesException {
        log.info(message);
        throw new InsufficientResourcesException();
    }
}
