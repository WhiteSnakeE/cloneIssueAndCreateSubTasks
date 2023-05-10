package org.example.services;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.example.repository.interfaces.JiraRepositorySubtaskCreator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class JiraServiceSubTaskCreator {
    private final JiraRepositorySubtaskCreator jiraRepositorySubtaskCreator;


    public JiraServiceSubTaskCreator (JiraRepositorySubtaskCreator jiraRepositorySubtaskCreator) {
        this.jiraRepositorySubtaskCreator = jiraRepositorySubtaskCreator;

    }

    public String createSubTask (IssueLink issueLink, String projectKey) {
        IssueInput subtask = new IssueInputBuilder().setProjectKey(projectKey).setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM"))).setIssueTypeId(10003L).setFieldValue("parent", ComplexIssueInputFieldValue.with("key", issueLink.getTargetIssueKey())).build();
        return jiraRepositorySubtaskCreator.createSubTask(subtask);

    }

}
