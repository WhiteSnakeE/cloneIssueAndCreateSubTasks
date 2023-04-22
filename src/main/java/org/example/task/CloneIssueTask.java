package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraServiceClone;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CloneIssueTask implements JavaDelegate {

    private final JiraServiceClone jiraServiceClone;

    public CloneIssueTask (JiraServiceClone jiraServiceClone) {
        this.jiraServiceClone = jiraServiceClone;
    }

    @Override
    public void execute (DelegateExecution delegateExecution) {
        String cloneKey =  jiraServiceClone.cloneIssue();
        log.info("issue cloned! ", cloneKey);
    }
}
