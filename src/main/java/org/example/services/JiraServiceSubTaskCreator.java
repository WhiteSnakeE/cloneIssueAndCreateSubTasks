package org.example.services;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.example.model.IssueLinkModel;
import org.example.repository.interfaces.JiraRepositoryIssue;
import org.example.repository.interfaces.JiraRepositorySubtaskCreator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class JiraServiceSubTaskCreator {
    private final JiraRepositorySubtaskCreator jiraRepositorySubtaskCreator;

    private final JiraRepositoryIssue jiraRepositoryIssue;

    public JiraServiceSubTaskCreator (JiraRepositorySubtaskCreator jiraRepositorySubtaskCreator,
            JiraRepositoryIssue jiraRepositoryIssue) {
        this.jiraRepositorySubtaskCreator = jiraRepositorySubtaskCreator;
        this.jiraRepositoryIssue = jiraRepositoryIssue;
    }

    public String createSubTask (IssueLink issueLink) {
        IssueInput subtask = new IssueInputBuilder()
                .setProjectKey(jiraRepositoryIssue.getIssue().getProject().getKey())
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .setFieldValue("parent", ComplexIssueInputFieldValue.with("key", issueLink.getTargetIssueKey()))
                .build();
        return jiraRepositorySubtaskCreator.createSubTask(subtask);

    }

}
