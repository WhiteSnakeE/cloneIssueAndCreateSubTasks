package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.model.IssueInstance;
import org.example.services.JiraServiceClone;
import org.springframework.stereotype.Component;

@Component("CloneIssue")
@Slf4j
public class CloneIssueTask implements JavaDelegate {

    private final JiraServiceClone jiraServiceClone;

    private final IssueInstance issueInstance;

    public CloneIssueTask (JiraServiceClone jiraServiceClone, IssueInstance issueInstance) {
        this.jiraServiceClone = jiraServiceClone;
        this.issueInstance = issueInstance;
    }

    @Override
    public void execute (DelegateExecution delegateExecution) {
//        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
//        String cloneKey =  jiraServiceClone.cloneIssue(issueInstance.getIssue());
//        processEnv.setCloneKey(cloneKey);
//        log.info("issue cloned! {} ", cloneKey);
    }
}
