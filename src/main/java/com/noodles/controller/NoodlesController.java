package com.noodles.controller;


import com.noodles.util.Constants;
import com.noodles.util.WorkflowLogger;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.apache.commons.lang.StringUtils;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @implSpec : Controller to start/resume cooking of veg masala noodles
 */
@RestController
public class NoodlesController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RuntimeService runtimeService;

    @PostMapping("/noodles/ready/{process-instance-id}")
    @Operation(summary = "cooked instant noodles", tags = {"noodles"})
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "204", description = "Cooking done", content = {@Content(schema = @Schema(hidden = true))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content(schema = @Schema(hidden = true))}),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "500", description = "Unexpected Error", content = {@Content(schema = @Schema(hidden = true))})})
    public ResponseEntity<String> cookedNoodles(
            @PathVariable(name = "process-instance-id") String processInstanceId
    ) {

        WorkflowLogger.info(logger, "Noodles Cooked", "Cooking done for process instance id: " + processInstanceId);

        try {
            if (StringUtils.isEmpty(processInstanceId)) {
                WorkflowLogger.error(logger, "Noodles Ready", "Process Instance Id cannot be null or empty");
                return ResponseEntity.badRequest().body("Process Instance Id cannot be null or empty");
            }

            runtimeService
                    .createMessageCorrelation(Constants.NOODLES_COOKED)
                    .processInstanceId(processInstanceId)
                    .correlate();

            return ResponseEntity.ok().body(processInstanceId + " is ready to eat.");
        } catch (Exception e) {
            WorkflowLogger.error(logger, "Noodles Ready", "Unknown Exception", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Unknown Exception. Message: " + e.getMessage());
        }

    }

}
