package org.example.jms.rmq.entity;

import lombok.Data;

@Data
public class TestEventEntity {

    private Long testId;

    private String testStatus;

    private Character testResult;
}
//{"testId":258,"testStatus":"FAIL","testResult":"F"}