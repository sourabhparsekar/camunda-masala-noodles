package com.noodles.controller;

import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class NoodlesControllerTest {

    @InjectMocks
    private NoodlesController noodlesController;

    @Mock(answer = Answers.RETURNS_DEEP_STUBS)
    private RuntimeService runtimeService;

    @BeforeEach
    void setUp() {

        MockitoAnnotations.openMocks(this);

        MessageCorrelationBuilder messageCorrelationBuilder = Mockito.mock(MessageCorrelationBuilder.class, RETURNS_DEEP_STUBS);
        doNothing().when(messageCorrelationBuilder).correlate();

        when(runtimeService.createMessageCorrelation(anyString()).setVariables(anyMap()).processInstanceId(anyString())).thenReturn(messageCorrelationBuilder);

        ReflectionTestUtils.setField(noodlesController, "runtimeService", runtimeService);


    }

    @Test
    void test_noodles_are_cooked() {

        ResponseEntity<String> responseEntity = noodlesController.cookedNoodles("28242b5c-d524-11eb-878f-dc7196c5d636");

        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("28242b5c-d524-11eb-878f-dc7196c5d636 is ready to eat.", responseEntity.getBody());

    }

    @Test
    void test_noodles_are_cooked_but_no_process_id() {

        ResponseEntity<String> responseEntity = noodlesController.cookedNoodles("");

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

    }


    @Test
    void test_noodles_are_cooked_but_some_exception() {

        doThrow(NullPointerException.class).when(runtimeService).createMessageCorrelation(anyString());

        ResponseEntity<String> responseEntity = noodlesController.cookedNoodles("28242b5c-d524-11eb-878f-dc7196c5d636");

        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());

    }

}