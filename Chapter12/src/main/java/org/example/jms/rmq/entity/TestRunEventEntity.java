package org.example.jms.rmq.entity;

import lombok.Data;

@Data
public class TestRunEventEntity {

    private Long testRunId;

    private Character testRunType;

    private Long runTime;

    private Character testRunResult;
}
//{"testRunId":105,"testRunType":"A","runTime":1702758254,"testRunResult":"S"}