package org.example.services;

import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.example.repository.interfaces.JiraRepositoryUpdate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class JiraServiceClone {

    private final JiraRepositoryUpdate jiraRepositoryUpdate;

    private static final String name = "CLONE - ";

    public JiraServiceClone (
            JiraRepositoryUpdate jiraRepositoryUpdate) {
        this.jiraRepositoryUpdate = jiraRepositoryUpdate;
    }

    public String setSubtaskLinkToClone(IssueLink issueLink,String cloneKey,String subTaskKey){
        jiraRepositoryUpdate.setLinkToIssue(issueLink.getTargetIssueKey(),cloneKey,LinkTypeEnum.RELATES.linkType);
        String key =  jiraRepositoryUpdate.setLinkToIssue(subTaskKey, cloneKey, LinkTypeEnum.RELATES.linkType);
        return key;
    }

    public String cloneIssue (Issue issue) {
        BasicIssue basicIssue = createCloneIssue(issue);
        jiraRepositoryUpdate.updateClone(basicIssue.getKey(), updateAssigneeAndAttachment(issue));
        jiraRepositoryUpdate.setLinkToIssue(issue.getKey(), basicIssue.getKey(),LinkTypeEnum.CLONERS.linkType);
        return basicIssue.getKey();
    }

    public BasicIssue createCloneIssue (Issue issue) {
        System.out.println(issue.getComponents());
        IssueInput issueInput = new IssueInputBuilder()
                .setSummary(name + issue.getSummary())
                .setDescription(issue.getDescription())
                .setPriority(issue.getPriority())
                .setIssueType(issue.getIssueType())
                .setAffectedVersions(issue.getAffectedVersions())
                .setFixVersions(issue.getFixVersions())
                .setProject(issue.getProject())
                .build();
        return jiraRepositoryUpdate.clone(issueInput);

    }

    public IssueInput updateAssigneeAndAttachment (Issue issue) {
        IssueInputBuilder builder = new IssueInputBuilder();
        Optional.ofNullable(issue.getComponents()).ifPresent(builder::setComponents);
        Optional.ofNullable(issue.getAttachments()).ifPresent(attachments -> builder.setFieldValue(String.valueOf(IssueFieldId.ATTACHMENT_FIELD), this.toListOfComplexIssueInputFieldValueWithSingleKey(attachments, "attachment")));
        return builder.build();
    }

    private <T> Iterable<ComplexIssueInputFieldValue> toListOfComplexIssueInputFieldValueWithSingleKey (
            Iterable<T> items, final String key) {
        return StreamSupport.stream(items.spliterator(), false).map(value -> ComplexIssueInputFieldValue.with(key, value)).collect(Collectors.toList());
    }
}
