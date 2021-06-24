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

/**
 * Mockito based JUnit 5 test to validate inputs
 */
class CheckIngredientsTest {

    DelegateExecution execution;

    @InjectMocks
    private CheckIngredients checkIngredients;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        execution = CamundaMockito.delegateExecutionFake();

    }

    @Test
    void test_I_have_noodles_water_pan_spatula() {

        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.NOODLES, true);
        variables.put(Constants.WATER, true);
        variables.put(Constants.PAN_SPATULA, true);
        execution.setVariables(variables);

        checkIngredients.execute(execution);

        //one additional variable added to execution
        Assertions.assertEquals((variables.size() + 1), execution.getVariables().size());

        //check all mandatory ingredients are avaialble
        Assertions.assertEquals(true, execution.getVariable(Constants.INGREDIENTS_AVAILABLE));

    }

    @Test
    void test_I_have_noodles_water_pan_spatula_vegetables_cheese() {

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

        checkIngredients.execute(execution);

        //one additional variable added to execution
        Assertions.assertEquals((variables.size() + 1), execution.getVariables().size());

        //check all mandatory ingredients are avaialble
        Assertions.assertEquals(true, execution.getVariable(Constants.INGREDIENTS_AVAILABLE));

    }

    @Test
    void test_I_do_not_have_noodles() {

        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.WATER, true);
        variables.put(Constants.PAN_SPATULA, true);
        execution.setVariables(variables);

        checkIngredients.execute(execution);

        //one additional variable added to execution
        Assertions.assertEquals((variables.size() + 1), execution.getVariables().size());

        //check all mandatory ingredients are avaialble
        Assertions.assertEquals(false, execution.getVariable(Constants.INGREDIENTS_AVAILABLE));

    }

    @Test
    void test_I_do_not_have_water() {

        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.NOODLES, true);
        variables.put(Constants.PAN_SPATULA, true);
        execution.setVariables(variables);

        checkIngredients.execute(execution);

        //one additional variable added to execution
        Assertions.assertEquals((variables.size() + 1), execution.getVariables().size());

        //check all mandatory ingredients are avaialble
        Assertions.assertEquals(false, execution.getVariable(Constants.INGREDIENTS_AVAILABLE));

    }

    @Test
    void test_I_do_not_have_pan_and_spatula() {

        Map<String, Object> variables = new HashMap<>();
        variables.put(Constants.NOODLES, true);
        variables.put(Constants.WATER, true);
        execution.setVariables(variables);

        checkIngredients.execute(execution);

        //one additional variable added to execution
        Assertions.assertEquals((variables.size() + 1), execution.getVariables().size());

        //check all mandatory ingredients are avaialble
        Assertions.assertEquals(false, execution.getVariable(Constants.INGREDIENTS_AVAILABLE));

    }


}