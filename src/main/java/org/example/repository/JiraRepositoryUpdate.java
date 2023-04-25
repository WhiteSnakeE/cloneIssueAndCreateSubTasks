package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;

public interface JiraRepositoryUpdate {
    BasicIssue clone (IssueInput issueInput);
    void updateClone(String key, IssueInput issueInputBuilder);

    void linkIssue(String keyFrom,String keyTo);

    void setSubtaskLinkToCLone(String s);

}
