package pl.harpi.colos.infrastructure.output.bpmn;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@Component("documentPreparationService")
public class DocumentPreparationService implements JavaDelegate {
  @Override
  public void execute(DelegateExecution execution) {
    String documentIdsInput = (String) execution.getVariable("documentIds");

    List<String> documentList = parseDocumentIds(documentIdsInput);

    validateInput(documentList);

    execution.setVariable("documentList", documentList);

    log.info("DocumentPreparationService.execute: done");
  }

  private List<String> parseDocumentIds(String input) {
    if (input == null || input.trim().isEmpty()) {
      return Collections.emptyList();
    }

    return Arrays.asList(input.split(","));
  }

  private void validateInput(List<String> documentList) {
    if (documentList.isEmpty()) {
      throw new BpmnError("VALIDATION_ERROR", "Document list cannot be empty");
    }
  }
}
