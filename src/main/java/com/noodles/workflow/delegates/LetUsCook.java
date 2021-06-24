package com.noodles.workflow.delegates;

import com.noodles.util.Constants;
import com.noodles.util.WorkflowLogger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.StringJoiner;

@Service("LetsCook")
public class LetUsCook implements JavaDelegate {

    public static final String STEP = "STEP ";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This will be used to fetch ingredients and begin to cook following the recipe
     *
     * @param execution : Process Variables will be retrieved from DelegateExecution
     */
    @Override
    public void execute(DelegateExecution execution) {

        WorkflowLogger.info(logger, "Prepare Noodles", "Follow below to make veg masala noodles");

        WorkflowLogger.info(logger, STEP + 1, "Take a deep-bottomed pan over medium flame and add water in it and bring it to a boil.");

        StringJoiner vegetables = new StringJoiner(", ");
        if (execution.hasVariable(Constants.ONION) && (boolean) execution.getVariable(Constants.ONION))
            vegetables.add(Constants.ONION);
        if (execution.hasVariable(Constants.TOMATO) && (boolean) execution.getVariable(Constants.TOMATO))
            vegetables.add(Constants.TOMATO);
        if (execution.hasVariable(Constants.CARROT) && (boolean) execution.getVariable(Constants.CARROT))
            vegetables.add(Constants.CARROT);
        if (execution.hasVariable(Constants.CAPSICUM) && (boolean) execution.getVariable(Constants.CAPSICUM))
            vegetables.add(Constants.CAPSICUM);

        if (vegetables.length() < 4)
            WorkflowLogger.info(logger, STEP + 2, "While the water boils, take a chopping board and chop " + vegetables);
        else
            WorkflowLogger.info(logger, STEP + 2, "While the water starts to boil, check if you received IMs on your mobile.");

        WorkflowLogger.info(logger, STEP + 3, "Once the water boils, add chopped vegetables, add 1 packet of instant noodles and stir it.");

        WorkflowLogger.info(logger, STEP + 4, "Add the taste-maker to it and give it another stir");

        if (execution.hasVariable(Constants.CHEESE) && (boolean) execution.getVariable(Constants.CHEESE))
            WorkflowLogger.info(logger, STEP + 5, "Add grated cheese and close the lid");
        else
            WorkflowLogger.info(logger, STEP + 5, "Close the lid");

        WorkflowLogger.info(logger, "Cooking in Process", "You can play with your mobile as it cooks for sometime...");

        execution.setVariable(Constants.IS_IT_COOKING, true);
    }
}
