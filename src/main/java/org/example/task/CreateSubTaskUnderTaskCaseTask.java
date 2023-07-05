package org.example.task;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;

import org.example.model.IssueInstance;
import org.example.services.JiraServiceSubTaskCreator;
import org.example.services.converter.IssueLinkConverter;
import org.springframework.stereotype.Component;

@Component("createSubTaskUnderTaskCase")
@Slf4j
public class CreateSubTaskUnderTaskCaseTask implements JavaDelegate {

    private final JiraServiceSubTaskCreator jiraServiceSubTaskCreator;

    private final IssueInstance issueInstance;

    private final IssueLinkConverter issueLinkConverter;


    public CreateSubTaskUnderTaskCaseTask (JiraServiceSubTaskCreator jiraServiceSubTaskCreator,
            IssueInstance issueInstance, IssueLinkConverter issueLinkConverter) {
        this.jiraServiceSubTaskCreator = jiraServiceSubTaskCreator;
        this.issueInstance = issueInstance;
        this.issueLinkConverter = issueLinkConverter;
    }

    @Override
    public void execute (DelegateExecution delegateExecution) {
//        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
//        IssueLink issueLink = issueLinkConverter.convertToIssueLink(processEnv.getTaskCase());
//        String key = jiraServiceSubTaskCreator.createSubTask(issueLink,issueInstance.getIssue().getProject().getKey());
//        processEnv.setSubtaskKey(key);
//        log.info("subtask key is {}",key);
    }
}
