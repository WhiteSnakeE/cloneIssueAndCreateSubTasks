package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.example.exeptions.IssueNotExistException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryRestCheck implements JiraRepositoryCheck {


    private final SearchRestClient searchRestClient;

    private Issue issue;

    public JiraRepositoryRestCheck (JiraRestClient jiraRestClient) {
        searchRestClient = jiraRestClient.getSearchClient();
    }

    @Override
    public SearchResult isProjectExist (String jql) {
        SearchResult searchResult;
        try {
            searchResult = searchRestClient.searchJql(jql, 1, 0, null).claim();
            issue = searchResult.getIssues().iterator().next();
            return searchResult;
        } catch (RestClientException e) {

            throw new IssueNotExistException(e);
        }
    }

    @Override
    public Issue getNeedIssue () {
      return issue;
    }

}
