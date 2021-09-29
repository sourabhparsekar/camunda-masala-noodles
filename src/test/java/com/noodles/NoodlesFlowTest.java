package com.noodles;

import com.noodles.util.Constants;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.camunda.bpm.engine.test.assertions.bpmn.BpmnAwareTests.assertThat;
import static org.mockito.Mockito.*;

public class NoodlesFlowTest extends ProcessFlowTest {

    public Map<String, Object> variables;

    @Mock
    private ProcessScenario scenario;

    @BeforeEach
    public void setup() {

        MockitoAnnotations.openMocks(this);

        //pep request variables
        variables = new HashMap<>();
        variables.put(Constants.NOODLES, true);
        variables.put(Constants.ONION, true);
        variables.put(Constants.TOMATO, true);
        variables.put(Constants.WATER, true);
        variables.put(Constants.CARROT, true);
        variables.put(Constants.CAPSICUM, true);
        variables.put(Constants.CHEESE, true);
        variables.put(Constants.PAN_SPATULA, true);
    }

    @Test
    void testCompleteTask() {

        //event based gateway response set for mocking
        when(scenario.waitsAtEventBasedGateway("IsItReady")).thenReturn(gateway -> {
            System.out.println("ReceivedEvent");
            Map recVariables = new HashMap<String, Object>();

            gateway.getEventSubscription("IsReady").receive(recVariables);
        });


        //this will trigger the BPMN Flow to invoke the cooking process
        Scenario handler = Scenario.run(scenario).startByKey("CookMasalaVeggiesNoodles", variables).execute();

        // check the final output if it has all the values
        assertThat(handler.instance(scenario)).variables().containsEntry(Constants.DID_WE_EAT_NOODLES, true);

        // check the order of the desired steps in the success flow
        verify(scenario, times(1)).hasFinished("Start_Process"); // process start
        verify(scenario, atMostOnce()).hasCompleted("CanWeCook"); // exclusive gateway
        verify(scenario, atMostOnce()).hasCompleted("LetsCook"); // cooking service step
        verify(scenario, atMostOnce()).hasCompleted("IsItReady"); // check for the event
        verify(scenario, atMostOnce()).hasCompleted("LetUsEat"); // ALl went well so call eat service
        verify(scenario, times(1)).hasFinished("End_Process"); // end event

    }

    @Test
    void testCookingDisasterTask() {

        //event based gateway timeout
        when(scenario.waitsAtEventBasedGateway("IsItReady")).thenReturn(gateway -> {
            System.out.println("Do Nothing to simulate a timeout");
        });

        //this will trigger the BPMN Flow to invoke the cooking process
        Scenario handler = Scenario.run(scenario).startByKey("CookMasalaVeggiesNoodles", variables).execute();

        // check the final output if it has all the values
        assertThat(handler.instance(scenario)).variables().containsEntry(Constants.DID_WE_EAT_NOODLES, false);

        // check the order of the desired steps in the success flow
        verify(scenario, times(1)).hasFinished("Start_Process"); // process start
        verify(scenario, atMostOnce()).hasCompleted("CanWeCook"); // exclusive gateway
        verify(scenario, atMostOnce()).hasCompleted("LetsCook"); // cooking service step
        verify(scenario, atMostOnce()).hasCompleted("IsItReady"); // check for the event
        verify(scenario, never()).hasStarted("LetUsEat"); // All did not go well so call to eat service fails
        verify(scenario, atMostOnce()).hasCompleted("OrderOnline"); // as it failed eat outside
        verify(scenario, times(1)).hasFinished("End_Process"); // end event

    }


}
