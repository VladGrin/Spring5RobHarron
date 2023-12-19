package org.example.rmq.notifier;

import com.google.gson.Gson;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.rmq.entity.TestEventEntity;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

@Slf4j
@Setter
public class TestEventNotifierRmq {

    private RabbitTemplate rabbitTemplate;

    private String exchange;

    private String routingKey;

    private Gson gson;

    public void sendRequest(TestEventEntity entity) {
        try {
            log.debug("Sending Request for [{}]", entity);
            String msgBody = gson.toJson(entity);
            Message rmqMsg = MessageBuilder.withBody(msgBody.getBytes())
                    .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                    .build();
            rabbitTemplate.send(exchange, routingKey, rmqMsg);
        } catch (Exception e) {
            log.error("Failed to send request [{}]. {}", entity, e.getMessage(), e);
        }
    }
}
