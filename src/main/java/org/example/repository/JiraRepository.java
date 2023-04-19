package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;


public interface JiraRepository {

    SearchResult isProjectExist(String jql);

    Issue getNeedIssue ();
}
