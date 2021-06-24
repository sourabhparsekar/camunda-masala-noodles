package com.noodles.workflow.delegates;

import com.noodles.util.Constants;
import com.noodles.util.WorkflowLogger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("OrderOnline")
public class OrderOnline implements JavaDelegate {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Cooking is no child's play. Let's order online
     *
     * @param execution : Process Variables will be retrieved from DelegateExecution
     */
    @Override
    public void execute(DelegateExecution execution) {

        WorkflowLogger.info(logger, "Order Online", "Veg masala noodles was no success.. Let's order online...");
        execution.setVariable(Constants.DID_WE_EAT_NOODLES, false);

        WorkflowLogger.info(logger, "Order Online", "Ordering is not part of this flow yet... Try your local apps...");
        execution.setVariable(Constants.ORDER_ONLINE, true);
    }
}
