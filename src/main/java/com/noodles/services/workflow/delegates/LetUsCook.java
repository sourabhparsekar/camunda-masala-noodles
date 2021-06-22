package com.noodles.services.workflow.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("LetsCook")
public class LetUsCook implements JavaDelegate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This will be used to fetch ingredients and begin to cook following the recipe
     * @param execution : Process Variables will be retrieved from DelegateExecution
     */
    @Override
    public void execute(DelegateExecution execution) {

    }
}
