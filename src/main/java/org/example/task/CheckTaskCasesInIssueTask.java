package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraServiceCheck;
import org.springframework.stereotype.Component;

@Component("CheckTaskCasesInIssue")
@Slf4j
public class CheckTaskCasesInIssueTask implements JavaDelegate {

    public final JiraServiceCheck jiraServiceCheck;

    public CheckTaskCasesInIssueTask (JiraServiceCheck jiraServiceCheck) {
        this.jiraServiceCheck = jiraServiceCheck;
    }

    @Override
    public void execute (DelegateExecution delegateExecution)  {
        boolean isTestCasesPresent = jiraServiceCheck.checkIfTestCasesExist();
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        processEnv.setAreNeedIssuesPresent(isTestCasesPresent);
        log.info("Are tasks present? {}",processEnv.getAreNeedIssuesPresent());
    }
}

