package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.SearchResult;


public interface JiraRepositoryCheck {

    SearchResult isProjectExist(String jql);

    Iterable<IssueLink> getIssueLinks();


}
