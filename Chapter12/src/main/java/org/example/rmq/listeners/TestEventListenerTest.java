package org.example.rmq.listeners;

import com.google.gson.Gson;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.rmq.entity.TestEventEntity;
import org.springframework.amqp.core.Message;

@Slf4j
@Setter
class TestEventListenerTest implements RabbitMqMessageListener {

    private Gson gson;

    @Override
    public void onMessage(Message message) {
        try {
            String msgStr = new String(message.getBody());
            log.debug("Received TestEvent message: {}", msgStr);
            TestEventEntity entity = gson.fromJson(msgStr, TestEventEntity.class);
            log.debug("Save TestEvent: {}", entity);
        } catch (Exception e) {
            log.warn("Exception during TestEventEntity procedure. {}", e.getMessage(), e);
        }
    }
}
