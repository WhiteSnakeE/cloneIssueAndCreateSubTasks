package org.example.task;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;

import org.example.services.JiraServiceSubTaskCreator;
import org.example.services.converter.IssueLinkConverter;
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
        IssueLink issueLink = new IssueLinkConverter().convertToIssueLink(processEnv.getTaskCase());
        String key = jiraServiceSubTaskCreator.createSubTask(issueLink);
        log.info("subtask key is {}",key);
    }
}
