package com.noodles.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WorkflowLoggerTest {

    @Test
    void test_Constructor_Is_Private() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<WorkflowLogger> constructor = WorkflowLogger.class.getDeclaredConstructor();
        assertTrue(Modifier.isPrivate(constructor.getModifiers()));
        constructor.setAccessible(true);
        try {
            constructor.newInstance();
        } catch (InvocationTargetException invocationTargetException) {
            Assertions.assertEquals(new IllegalStateException("Logger Utility Class. Cannot be instantiated.").getMessage(), invocationTargetException.getCause().getMessage());
        }
    }

}