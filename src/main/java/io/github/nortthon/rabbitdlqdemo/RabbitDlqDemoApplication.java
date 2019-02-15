package io.github.nortthon.rabbitdlqdemo;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static io.github.nortthon.rabbitdlqdemo.RabbitConfig.EXCHANGE_NAME;
import static io.github.nortthon.rabbitdlqdemo.RabbitConfig.ROUTING_KEY_NAME;

@SpringBootApplication
public class RabbitDlqDemoApplication implements ApplicationRunner {

	private final AmqpTemplate amqpTemplate;

	@Autowired
	public RabbitDlqDemoApplication(AmqpTemplate amqpTemplate) {
		this.amqpTemplate = amqpTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(RabbitDlqDemoApplication.class, args);
	}

	@Override
	public void run(ApplicationArguments args) {
		amqpTemplate.convertAndSend(EXCHANGE_NAME, ROUTING_KEY_NAME, "message");
	}
}

