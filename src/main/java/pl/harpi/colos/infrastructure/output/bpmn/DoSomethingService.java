package pl.harpi.colos.infrastructure.output.bpmn;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DoSomethingService implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String documentId = (String) execution.getVariable("documentId");

    switch (documentId) {
      case "1" -> {
        log.info("DoSomethingService.execute: error 1");
        throw new RuntimeException("Runtime Error 1");
      }
      case "7" -> {
        log.info("DoSomethingService.execute: business error 7");
        throw new BpmnError("BUSINESS_ERROR", "Business Error 7");
      }
      default -> log.info("DoSomethingService.execute: normal {}", documentId);
    }

    log.info("DoSomethingService.execute: done {}", documentId);
  }
}
