package pl.harpi.colos.infrastructure.output.bpmn;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Random;

@Slf4j
@Component
public class PrepareOutputService implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) throws Exception {
    String documentId = (String) execution.getVariable("documentId");

    int sleepTime = new Random().nextInt(10000);

    Thread.sleep(sleepTime);

    log.info("PrepareOutputService.execute: done {} in {} ms", documentId, sleepTime);
  }
}
