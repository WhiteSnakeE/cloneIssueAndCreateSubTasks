package org.example.repository.interfaces;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;

import java.util.List;

public interface JiraRepositoryIssue {

    Issue getIssue ();

    List<IssueLink> getIssueLinks();

    void setIssueLinks(List<IssueLink> issueLinks );


}
