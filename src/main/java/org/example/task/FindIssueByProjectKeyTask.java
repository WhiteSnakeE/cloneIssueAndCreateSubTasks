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
        String input = (String) delegateExecution.getVariable("IssueKey");
        log.info("project key is {}", input);
        String response = jiraServiceCheck.checkIfIssueExist(input);
        processEnv.setIssueKey(response);
        log.info("project key is {}", response);
    }
}
