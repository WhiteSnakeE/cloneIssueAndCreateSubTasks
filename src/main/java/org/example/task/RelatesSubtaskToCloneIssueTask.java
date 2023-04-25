package org.example.task;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.services.JiraServiceClone;
import org.example.services.JiraServiceSubTaskCreator;
import org.springframework.stereotype.Component;

@Component("relatesSubtaskToCloneIssue")
public class RelatesSubtaskToCloneIssueTask implements JavaDelegate {

    private final JiraServiceClone jiraServiceClone;
    private final JiraServiceSubTaskCreator jiraServiceSubTaskCreator;

    public RelatesSubtaskToCloneIssueTask (JiraServiceClone jiraServiceClone,
            JiraServiceSubTaskCreator jiraServiceSubTaskCreator) {
        this.jiraServiceClone = jiraServiceClone;
        this.jiraServiceSubTaskCreator = jiraServiceSubTaskCreator;
    }

    @Override
    public void execute (DelegateExecution delegateExecution)  {
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        IssueLink issueLink =  jiraServiceSubTaskCreator.convertIssueLink(processEnv.getTaskCase());
        jiraServiceClone.setSubtaskLinkToClone(issueLink);
    }
}
