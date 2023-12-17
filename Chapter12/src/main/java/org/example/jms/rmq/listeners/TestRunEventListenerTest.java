package org.example.jms.rmq.listeners;

import com.google.gson.Gson;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.jms.rmq.entity.TestRunEventEntity;
import org.springframework.amqp.core.Message;

@Slf4j
@Setter
class TestRunEventListenerTest implements RabbitMqMessageListener {

    private Gson gson;

    @Override
    public void onMessage(Message message) {
        try {
            String msgStr = new String(message.getBody());
            log.debug("Received TestRunEvent message: {}", msgStr);
            TestRunEventEntity entity = gson.fromJson(msgStr, TestRunEventEntity.class);
            log.debug("Save TestRunEvent: {}", entity);
        } catch (Exception e) {
            log.warn("Exception during TestRunEvent procedure. {}", e.getMessage(), e);
        }
    }
}
