package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraServiceCheck;
import org.springframework.stereotype.Component;

@Component("FindIssueByProjectKey")
@Slf4j
public class FindIssueByProjectKeyTask implements JavaDelegate {

    private final JiraServiceCheck jiraServiceCheck;

    public FindIssueByProjectKeyTask (JiraServiceCheck jiraServiceCheck) {
        this.jiraServiceCheck = jiraServiceCheck;
    }

    @Override
    public void execute (DelegateExecution delegateExecution)  {
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        String response = (String) delegateExecution.getVariable("IssueKey");
        String issueKey = jiraServiceCheck.checkIfIssueExist(response);
        processEnv.setIssueKey(issueKey);
        System.out.println(issueKey);
    }
}
