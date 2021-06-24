package com.noodles.workflow.delegates;

import com.noodles.util.Constants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class OrderOnlineTest {

    DelegateExecution execution;

    @InjectMocks
    private OrderOnline orderOnline;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        execution = CamundaMockito.delegateExecutionFake();
    }


    @Test
    void test_order_online() {
        orderOnline.execute(execution);
        Assertions.assertEquals(false, execution.getVariable(Constants.DID_WE_EAT_NOODLES));
        Assertions.assertEquals(true, execution.getVariable(Constants.ORDER_ONLINE));
    }
}