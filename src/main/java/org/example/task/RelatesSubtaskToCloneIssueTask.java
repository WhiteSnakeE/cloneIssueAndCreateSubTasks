package org.example.task;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraServiceClone;
import org.springframework.stereotype.Component;

@Component("relatesSubtaskToCloneIssue")
public class RelatesSubtaskToCloneIssueTask implements JavaDelegate {

    private final JiraServiceClone jiraServiceClone;

    public RelatesSubtaskToCloneIssueTask (JiraServiceClone jiraServiceClone) {
        this.jiraServiceClone = jiraServiceClone;
    }

    @Override
    public void execute (DelegateExecution delegateExecution)  {
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        jiraServiceClone.setSubtaskLinkToClone(processEnv.getTaskCase());
    }
}
