package org.example.repository.interfaces;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;

public interface JiraRepositoryUpdate {
    BasicIssue clone (IssueInput issueInput);

    void updateClone(String key, IssueInput issueInputBuilder);

    String setLinkToIssue(String keyFrom,String keyTo,String linkType);

}
