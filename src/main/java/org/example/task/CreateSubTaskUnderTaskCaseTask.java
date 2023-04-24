package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraServiceSubTaskCreator;
import org.springframework.stereotype.Component;

@Component("createSubTaskUnderTaskCase")
@Slf4j
public class CreateSubTaskUnderTaskCaseTask implements JavaDelegate {

    private final JiraServiceSubTaskCreator jiraServiceSubTaskCreator;

    public CreateSubTaskUnderTaskCaseTask (JiraServiceSubTaskCreator jiraServiceSubTaskCreator) {
        this.jiraServiceSubTaskCreator = jiraServiceSubTaskCreator;
    }

    @Override
    public void execute (DelegateExecution delegateExecution) {
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        String key = jiraServiceSubTaskCreator.createSubTask(processEnv.getTaskCase());
        log.info("subtask key is {}",key);
    }
}
