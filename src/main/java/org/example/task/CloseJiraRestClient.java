package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.repository.interfaces.JiraRepositoryCheck;
import org.springframework.stereotype.Component;

@Component("CloseJiraRestClient")
@Slf4j
public class CloseJiraRestClient implements JavaDelegate {

    private final JiraRepositoryCheck jiraRepositoryCheck;

    public CloseJiraRestClient(JiraRepositoryCheck jiraRepositoryCheck) {
        this.jiraRepositoryCheck = jiraRepositoryCheck;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        jiraRepositoryCheck.close();
        log.info("Process was ended!");

    }
}
