package org.example.repository.interfaces;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;

import java.util.List;

public interface JiraRepositoryIssue {

    Issue getIssue ();

    String getCloneKey();

    void setCloneKey(String cloneKey);

    String getSubtaskKey();

    void setSubtaskKey(String subtaskKey);

    List<IssueLink> getIssueLinks();

    void setIssueLinks(List<IssueLink> issueLinks );


}
