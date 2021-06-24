package com.noodles.workflow.delegates;

import com.noodles.util.Constants;
import com.noodles.util.WorkflowLogger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("LetUsEat")
public class LetUsEat implements JavaDelegate {

    public static final String EAT_NOODLES = "Eat Noodles";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * We will eat what we cooked if it was not burnt
     *
     * @param execution : Process Variables will be retrieved from DelegateExecution
     */
    @Override
    public void execute(DelegateExecution execution) {

        WorkflowLogger.info(logger, EAT_NOODLES, "Veg masala noodles is ready. Let's eat... But first serve it..");

        WorkflowLogger.info(logger, EAT_NOODLES, "Transfer to a serving bowl and sprinkle a pinch of chaat masala or oregano over the noodles to make it even more flavorful.");

        if (execution.hasVariable(Constants.CHEESE) && (boolean) execution.getVariable(Constants.CHEESE))
            WorkflowLogger.info(logger, EAT_NOODLES, "Add grated cheese over it. ");

        WorkflowLogger.info(logger, EAT_NOODLES, "Serve it hot to enjoy!! ");

        execution.setVariable(Constants.DID_WE_EAT_NOODLES, true);
    }
}
