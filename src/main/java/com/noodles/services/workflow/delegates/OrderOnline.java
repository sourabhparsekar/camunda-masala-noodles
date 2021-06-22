package com.noodles.services.workflow.delegates;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("OrderOnline")
public class OrderOnline  implements JavaDelegate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Cooking is no child's play. Let's order online
     * @param execution : Process Variables will be retrieved from DelegateExecution
     */
    @Override
    public void execute(DelegateExecution execution) {

    }
}
