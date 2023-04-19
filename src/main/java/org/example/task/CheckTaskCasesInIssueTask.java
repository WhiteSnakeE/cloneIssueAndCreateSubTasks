package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraService;
import org.springframework.stereotype.Component;

@Component("CheckTaskCasesInIssue")
@Slf4j
public class CheckTaskCasesInIssueTask implements JavaDelegate {

    public final JiraService jiraService;

    public CheckTaskCasesInIssueTask (JiraService jiraService) {
        this.jiraService = jiraService;
    }

    @Override
    public void execute (DelegateExecution delegateExecution)  {
        boolean isTestCasesPresent = jiraService.checkIfTestCasesExist();
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        processEnv.setAreNeedIssuesPresent(isTestCasesPresent);
        log.info("Are tasks present? {}",processEnv.getAreNeedIssuesPresent());
    }
}

