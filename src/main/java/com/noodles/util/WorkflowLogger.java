package com.noodles.util;

import org.slf4j.Logger;

/**
 * @implSpec Logger to trace the flow using keyword workflow-service-info
 */
public final class WorkflowLogger {

    private static final String INFO_LOGGER = "Timestamp:{}:workflow-service-info:{}:{}";
    private static final String ERROR_LOGGER = "Timestamp:{}:workflow-service-error:{}:{}";

    private WorkflowLogger() {
        throw new IllegalStateException("Logger Utility Class. Cannot be instantiated.");
    }

    /**
     * INFO log statement
     *
     * @param logger  : logger used in the calling class
     * @param method  : method description
     * @param message : processing details
     */
    public static void info(Logger logger, String method, String message) {
        logger.info(INFO_LOGGER, System.currentTimeMillis(), method, message);
    }


    /**
     * ERROR log statement with exception trace
     *
     * @param logger    : logger used in the calling class
     * @param method    : method description
     * @param message   : processing details
     * @param exception : exception trace details
     */
    public static void error(Logger logger, String method, String message, Exception exception) {
        logger.error(ERROR_LOGGER, System.currentTimeMillis(), method, message, exception);
    }


    /**
     * ERROR log statement
     *
     * @param logger  : logger used in the calling class
     * @param method  : method description
     * @param message : processing details
     */
    public static void error(Logger logger, String method, String message) {
        logger.error(ERROR_LOGGER, System.currentTimeMillis(), method, message);
    }
}
