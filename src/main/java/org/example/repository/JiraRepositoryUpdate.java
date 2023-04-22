package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;

public interface JiraRepositoryUpdate {
    BasicIssue clone (IssueInput issueInput);
    void updateClone(String key, IssueInput issueInput);

    void linkIssue(String keyFrom,String keyTo);

}
