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

import static java.lang.Boolean.TRUE;

class LetUsCookTest {

    DelegateExecution execution;

    @InjectMocks
    private LetUsCook letUsCook;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        execution = CamundaMockito.delegateExecutionFake();

    }

    @Test
    void test_I_have_all_ingredients() {

        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.NOODLES, true);
        variables.put(Constants.WATER, true);
        variables.put(Constants.PAN_SPATULA, true);
        variables.put(Constants.ONION, true);
        variables.put(Constants.TOMATO, true);
        variables.put(Constants.CHEESE, true);
        variables.put(Constants.CARROT, true);
        variables.put(Constants.CAPSICUM, true);
        execution.setVariables(variables);

        letUsCook.execute(execution);

        Assertions.assertEquals(TRUE, execution.getVariable(Constants.IS_IT_COOKING));
    }

    @Test
    void test_I_have_minimum_ingredients() {

        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.NOODLES, true);
        variables.put(Constants.WATER, true);
        variables.put(Constants.PAN_SPATULA, true);
        execution.setVariables(variables);

        letUsCook.execute(execution);

        Assertions.assertEquals(TRUE, execution.getVariable(Constants.IS_IT_COOKING));
    }
}