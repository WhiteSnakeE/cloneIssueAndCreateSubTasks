package org.example.services;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.example.repository.JiraRepositoryClone;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JiraServiceClone {

    private final JiraRepositoryClone jiraRepositoryClone;


    public JiraServiceClone (JiraRepositoryClone jiraRepositoryClone) {
        this.jiraRepositoryClone = jiraRepositoryClone;
    }
    public Issue cloneIssue(){
        Issue issue  = jiraRepositoryClone.cloneIssue();
        String name = "CLONE - ";
        IssueInput issueInput = new IssueInputBuilder()
                .setSummary(name + issue.getSummary())
                .setDescription(issue.getDescription())
                .setPriority(issue.getPriority())
                .setIssueType(issue.getIssueType())
                .setAffectedVersions(issue.getAffectedVersions())
                .setFixVersions(issue.getFixVersions())
                .setProject(issue.getProject())
                .setFieldValue(String.valueOf(IssueFieldId.ATTACHMENT_FIELD),
                        this.toListOfComplexIssueInputFieldValueWithSingleKey(issue.getAttachments(),
                                "attachment"))
                .build();

        return null;

    }
    private <T> Iterable<ComplexIssueInputFieldValue> toListOfComplexIssueInputFieldValueWithSingleKey(Iterable<T> items, final String key) {
        return StreamSupport.stream(items.spliterator(),
                        false).map(value -> ComplexIssueInputFieldValue.with(key, value))
                .collect(Collectors.toList());
    }
}
