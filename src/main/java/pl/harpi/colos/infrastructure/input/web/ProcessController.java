package pl.harpi.colos.infrastructure.input.web;

import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/process")
public class ProcessController {
    private final RuntimeService runtimeService;

    @GetMapping("/start")
    public String startProcess() {
        Map<String, Object> variables = new HashMap<>();

        variables.put("documentIds", "1,2,3,4,5,6,7,8,9,10");

        runtimeService.startProcessInstanceByKey("Process_NewTicket", UUID.randomUUID().toString(), variables);
        return "OK";
    }

    @GetMapping("/list")
    public String listProcesses() {
        return runtimeService.createProcessInstanceQuery().list().toString();
    }
}
