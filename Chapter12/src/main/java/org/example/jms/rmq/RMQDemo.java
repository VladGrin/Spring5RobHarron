package org.example.jms.rmq;

import org.example.jms.rmq.entity.TestEventEntity;
import org.example.jms.rmq.entity.TestRunEventEntity;
import org.example.jms.rmq.notifier.TestEventNotifierRmq;
import org.example.jms.rmq.notifier.TestRunEventNotifierRmq;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class RMQDemo {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("./spring/applicationContext-rmq.xml");
        Thread.sleep(3000L);

        TestEventNotifierRmq testEventNotifierRmq = ctx.getBean("testEventNotifierRmq", TestEventNotifierRmq.class);

        TestEventEntity entity1 = new TestEventEntity();
        entity1.setTestId(102L);
        entity1.setTestStatus("ERROR");
        entity1.setTestResult('T');
        testEventNotifierRmq.sendRequest(entity1);

        Thread.sleep(3000L);

        TestRunEventNotifierRmq testRunEventNotifierRmq = ctx.getBean("testRunEventNotifierRmq", TestRunEventNotifierRmq.class);

        TestRunEventEntity entity2 = new TestRunEventEntity();
        entity2.setTestRunId(456L);
        entity2.setTestRunType('G');
        entity2.setRunTime(1702758254L);
        entity2.setTestRunResult('P');
        testRunEventNotifierRmq.sendRequest(entity2);

        Thread.sleep(10000L);
    }
}
