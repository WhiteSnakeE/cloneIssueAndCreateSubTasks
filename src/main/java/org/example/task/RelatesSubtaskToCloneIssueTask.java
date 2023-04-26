package org.example.task;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraServiceClone;
import org.example.services.converter.IssueLinkConverter;
import org.springframework.stereotype.Component;

@Component("relatesSubtaskToCloneIssue")
@Slf4j
public class RelatesSubtaskToCloneIssueTask implements JavaDelegate {

    private final JiraServiceClone jiraServiceClone;

    public RelatesSubtaskToCloneIssueTask (JiraServiceClone jiraServiceClone) {
        this.jiraServiceClone = jiraServiceClone;
    }

    @Override
    public void execute (DelegateExecution delegateExecution) {
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        IssueLink issueLink = new IssueLinkConverter().convertToIssueLink(processEnv.getTaskCase());
        String key = jiraServiceClone.setSubtaskLinkToClone(issueLink, processEnv.getCloneKey(), processEnv.getSubtaskKey());
        processEnv.setSubtaskKey(key);
        log.info("subtask with key {} was related",key);
    }
}
