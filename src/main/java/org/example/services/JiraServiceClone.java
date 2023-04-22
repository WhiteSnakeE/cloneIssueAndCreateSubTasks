package org.example.services;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueFieldId;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.example.repository.JiraRepositoryIssue;
import org.example.repository.JiraRepositoryUpdate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JiraServiceClone {

    private final JiraRepositoryIssue jiraRepositoryIssue;

    private final JiraRepositoryUpdate jiraRepositoryUpdate;

    private static final String name = "CLONE - ";


    public JiraServiceClone (@Qualifier("jiraRepositoryRestIssue") JiraRepositoryIssue jiraRepositoryIssue,
            JiraRepositoryUpdate jiraRepositoryUpdate) {
        this.jiraRepositoryIssue = jiraRepositoryIssue;
        this.jiraRepositoryUpdate = jiraRepositoryUpdate;
    }

    public String cloneIssue () {
        Issue issue = jiraRepositoryIssue.getIssue();
        BasicIssue basicIssue = jiraRepositoryUpdate.clone(createCloneIssue(issue));
        jiraRepositoryUpdate.updateClone(basicIssue.getKey(), updateNotNullIssueFields(issue));
        jiraRepositoryUpdate.linkIssue(jiraRepositoryIssue.getIssue().getKey(), basicIssue.getKey());
        jiraRepositoryIssue.setCloneKey(basicIssue.getKey());
        return basicIssue.getKey();

    }

    private IssueInput createCloneIssue (Issue issue) {
        return new IssueInputBuilder().setSummary(name + issue.getSummary()).setDescription(issue.getDescription()).setPriority(issue.getPriority()).setIssueType(issue.getIssueType()).setAffectedVersions(issue.getAffectedVersions()).setFixVersions(issue.getFixVersions()).setProject(issue.getProject()).setFieldValue(String.valueOf(IssueFieldId.ATTACHMENT_FIELD), this.toListOfComplexIssueInputFieldValueWithSingleKey(issue.getAttachments(), "attachment")).build();
    }

    private IssueInput updateNotNullIssueFields (Issue issue) {
        IssueInputBuilder builder = new IssueInputBuilder();
        Optional.ofNullable(issue.getAssignee()).ifPresent(builder::setAssignee);
        Optional.ofNullable(issue.getAttachments()).ifPresent(attachments -> builder.setFieldValue(String.valueOf(IssueFieldId.ATTACHMENT_FIELD), this.toListOfComplexIssueInputFieldValueWithSingleKey(attachments, "attachment")));
        return builder.build();
    }

    private <T> Iterable<ComplexIssueInputFieldValue> toListOfComplexIssueInputFieldValueWithSingleKey (
            Iterable<T> items, final String key) {
        return StreamSupport.stream(items.spliterator(), false).map(value -> ComplexIssueInputFieldValue.with(key, value)).collect(Collectors.toList());
    }
}
