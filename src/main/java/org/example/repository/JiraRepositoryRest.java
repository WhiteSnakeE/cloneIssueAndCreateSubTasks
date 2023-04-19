package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.example.exeptions.IssueNotExistException;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryRest implements JiraRepository {


    private final SearchRestClient searchRestClient;

    public JiraRepositoryRest(JiraRestClient jiraRestClient) {
        searchRestClient = jiraRestClient.getSearchClient();
    }

    @Override
    public SearchResult getIssuesFields(String jql, int maxResults) {

        try {
            return searchRestClient.searchJql(jql, maxResults, 0, null).get(10,TimeUnit.SECONDS);
        } catch (InterruptedException | TimeoutException | ExecutionException | NullPointerException e) {
            e.printStackTrace();
            throw new BpmnError("SOLVIT_ERROR", e.getMessage());
        }
    }

    @Override
    public SearchResult isProjectExist (String jql) {
        try {
            return searchRestClient.searchJql(jql,1,0,null).claim();
        } catch (RestClientException e) {

            throw new IssueNotExistException(e);
        }
    }

}
