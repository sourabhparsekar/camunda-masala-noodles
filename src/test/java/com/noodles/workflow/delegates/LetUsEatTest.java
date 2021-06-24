package com.noodles.workflow.delegates;

import com.noodles.util.Constants;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.extension.mockito.CamundaMockito;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

class LetUsEatTest {

    DelegateExecution execution;

    @InjectMocks
    private LetUsEat letUsEat;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        execution = CamundaMockito.delegateExecutionFake();
    }

    @Test
    void test_With_Cheese() {
        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.CHEESE, true);
        execution.setVariables(variables);
        letUsEat.execute(execution);
        Assertions.assertEquals(true, execution.getVariable(Constants.DID_WE_EAT_NOODLES));
    }

    @Test
    void test_Without_Cheese() {
        letUsEat.execute(execution);
        Assertions.assertEquals(true, execution.getVariable(Constants.DID_WE_EAT_NOODLES));
    }
}