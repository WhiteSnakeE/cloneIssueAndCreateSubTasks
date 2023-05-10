package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.model.IssueInstance;
import org.example.services.JiraServiceCheck;
import org.springframework.stereotype.Component;

@Component("CheckTaskCasesInIssue")
@Slf4j
public class CheckTaskCasesInIssueTask implements JavaDelegate {

    public final JiraServiceCheck jiraServiceCheck;

    public final IssueInstance issueInstance;

    public CheckTaskCasesInIssueTask (JiraServiceCheck jiraServiceCheck, IssueInstance issueInstance) {
        this.jiraServiceCheck = jiraServiceCheck;
        this.issueInstance = issueInstance;
    }

    @Override
    public void execute (DelegateExecution delegateExecution)  {
        boolean isTestCasesPresent = jiraServiceCheck.checkIfTestCasesExist(issueInstance.getIssue());
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        processEnv.setAreNeedIssuesPresent(isTestCasesPresent);
        log.info("Are tasks present? {}",processEnv.getAreNeedIssuesPresent());
    }
}

