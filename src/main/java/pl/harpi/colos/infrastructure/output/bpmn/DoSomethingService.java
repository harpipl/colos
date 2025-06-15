package pl.harpi.colos.infrastructure.output.bpmn;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.camunda.bpm.engine.ManagementService;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.camunda.bpm.engine.runtime.Job;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DoSomethingService implements JavaDelegate {
  private final ManagementService managementService;

  @Override
  public void execute(DelegateExecution execution) throws Exception {
    val processInstanceId = execution.getProcessInstanceId();
    val documentId = (String) execution.getVariable("documentId");

    switch (documentId) {
      case "1" -> {
        if (shouldStopRetry(processInstanceId)) {
          throw new BpmnError("RUNTIME_ERROR", "Runtime Error 1 - retries left: ");
        } else {
          throw new RuntimeException("Runtime Error 1 - retries left: ");
        }
      }
      case "7" -> {
        log.info("DoSomethingService.execute: business error 7");
        throw new BpmnError("BUSINESS_ERROR", "Business Error 7");
      }
      default -> log.info("DoSomethingService.execute: normal {}", documentId);
    }

    log.info("DoSomethingService.execute: done {}", documentId);
  }

  private boolean shouldStopRetry(String processInstanceId) {
    Job job =
        managementService.createJobQuery().processInstanceId(processInstanceId).singleResult();

    int retriesLeft = job != null ? job.getRetries() : 0;

    if (job == null) {
      log.info("DoSomethingService.shouldStopRetry: no job found");
    } else {
      log.info("DoSomethingService.shouldStopRetry: " + job.getRetries());
    }

    return retriesLeft <= 1;
  }
}
