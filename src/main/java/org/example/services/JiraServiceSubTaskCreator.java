package org.example.services;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import com.atlassian.jira.rest.client.api.domain.IssueType;
import com.atlassian.jira.rest.client.api.domain.Subtask;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.example.model.IssueLinkModel;
import org.example.repository.JiraRepositoryIssue;
import org.example.repository.JiraRepositorySubTask;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class JiraServiceSubTaskCreator {
    private final JiraRepositorySubTask jiraRepositorySubTask;

    private final JiraRepositoryIssue jiraRepositoryIssue;

    public JiraServiceSubTaskCreator (JiraRepositorySubTask jiraRepositorySubTask,
            JiraRepositoryIssue jiraRepositoryIssue) {
        this.jiraRepositorySubTask = jiraRepositorySubTask;
        this.jiraRepositoryIssue = jiraRepositoryIssue;
    }

    public String createSubTask (IssueLink issueLink) {
        IssueInput subtask = new IssueInputBuilder()
                .setProjectKey(jiraRepositoryIssue.getIssue().getProject().getKey())
                .setSummary("Test Run  " + LocalDate.now().getDayOfMonth() + "." + LocalDate.now().getMonthValue())
                .setIssueTypeId(10003L)
                .setFieldValue("parent", ComplexIssueInputFieldValue.with("key", issueLink.getTargetIssueKey()))
                .build();
        return jiraRepositorySubTask.createSubTask(subtask);

    }

    public IssueLink convertIssueLink (IssueLinkModel issueLinkModel) {
        IssueLinkType issueLinkType = new IssueLinkType(issueLinkModel.getName(), issueLinkModel.getDescription(), issueLinkModel.getDirection());
        return new IssueLink(issueLinkModel.getTargetIssueKey(), issueLinkModel.getTargetIssueUri(), issueLinkType);


    }


}
