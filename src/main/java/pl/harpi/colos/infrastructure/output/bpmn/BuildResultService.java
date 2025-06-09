package pl.harpi.colos.infrastructure.output.bpmn;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class BuildResultService implements JavaDelegate {
  @Override
  public void execute(DelegateExecution delegateExecution) throws Exception {
    log.info("BuildResultService.execute: done");
  }
}
