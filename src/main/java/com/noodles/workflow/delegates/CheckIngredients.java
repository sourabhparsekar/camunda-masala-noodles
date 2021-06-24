package com.noodles.workflow.delegates;

import com.noodles.util.Constants;
import com.noodles.util.WorkflowLogger;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("CheckIngredients")
public class CheckIngredients implements JavaDelegate {

    public static final String CHECK_INGREDIENTS = "Check Ingredients";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * This will be used to check if we have all ingredients to cook
     *
     * @param execution : Process Variables will be retrieved from DelegateExecution
     */
    @Override
    public void execute(DelegateExecution execution) {

        WorkflowLogger.info(logger, CHECK_INGREDIENTS, "Check ingredients to make veg masala noodles");

        boolean ingredientsAvailable;

        //in all the ingredients,
        //mandatory : instant noodles, water, pan & spatula
        //optional : veggies,

        boolean instantNoodles = getFormField(execution, Constants.NOODLES);
        boolean water = getFormField(execution, Constants.WATER);
        boolean panSpatula = getFormField(execution, Constants.PAN_SPATULA);

        if (instantNoodles && water && panSpatula) {
            ingredientsAvailable = true;
            WorkflowLogger.info(logger, CHECK_INGREDIENTS, "we can make veg masala noodles");
        } else {
            ingredientsAvailable = false;
            WorkflowLogger.error(logger, CHECK_INGREDIENTS, "we cannot make veg masala noodles as required ingredient is missing. Instant Noodles, Water, Pan and Spatula are required.");
        }

        execution.setVariable(Constants.INGREDIENTS_AVAILABLE, ingredientsAvailable);

    }

    /**
     * @param execution : delegate execution to extract process instance variables
     * @param formField : form field to be extracted
     * @return
     */
    private boolean getFormField(DelegateExecution execution, String formField) {
        if (execution.hasVariable(formField))
            return (boolean) execution.getVariable(formField);
        else return false;
    }


}
