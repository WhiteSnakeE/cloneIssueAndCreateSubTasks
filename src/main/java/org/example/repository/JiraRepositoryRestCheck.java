package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.example.exeptions.IssueNotExistException;
import org.example.model.IssueInstance;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryRestCheck implements JiraRepositoryCheck {


    private final SearchRestClient searchRestClient;

    private final IssueInstance issueInstance;

    public JiraRepositoryRestCheck (JiraRestClient jiraRestClient, IssueInstance issueInstance) {
        searchRestClient = jiraRestClient.getSearchClient();
        this.issueInstance = issueInstance;
    }

    @Override
    public SearchResult isProjectExist (String jql) {
        SearchResult searchResult;
        try {
            searchResult = searchRestClient.searchJql(jql, 1, 0, null).claim();
            issueInstance.setIssue(searchResult.getIssues().iterator().next());
            return searchResult;
        } catch (RestClientException e) {

            throw new IssueNotExistException(e);
        }
    }

    @Override
    public Iterable<IssueLink> getIssueLinks () {
        return issueInstance.getIssue().getIssueLinks();
    }
}
