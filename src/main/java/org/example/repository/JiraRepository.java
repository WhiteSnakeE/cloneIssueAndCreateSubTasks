package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.SearchResult;


public interface JiraRepository {

    SearchResult getIssuesFields(String jql, int maxResults);

    SearchResult isProjectExist(String jql);

}
