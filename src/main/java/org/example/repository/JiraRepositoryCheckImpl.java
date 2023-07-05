package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.example.configuration.JiraConfiguration;
import org.example.model.IssueInstance;
import org.example.repository.interfaces.JiraRepositoryCheck;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryCheckImpl implements JiraRepositoryCheck {

    private final JiraConfiguration jiraConfiguration;

    private final IssueInstance issueInstance;

    public JiraRepositoryCheckImpl (JiraConfiguration jiraConfiguration, IssueInstance issueInstance) {
        this.jiraConfiguration = jiraConfiguration;
        this.issueInstance = issueInstance;
    }

    @Override
    public SearchResult isProjectExist (String jql) {
        JiraRestClient jiraRestClient = jiraConfiguration.getJiraRestClient();
        SearchRestClient searchRestClient = jiraRestClient.getSearchClient();
        SearchResult searchResult;
//        log.info("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        try {
            searchResult = searchRestClient.searchJql(jql, 1, 0, null).get(10, TimeUnit.SECONDS);
            issueInstance.setIssue(searchResult.getIssues().iterator().next());
            return searchResult;
        } catch (ExecutionException | RestClientException e) {
//            log.info("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
            throw new BpmnError("IssueNotExistError");
        } catch (TimeoutException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        jiraConfiguration.close();
    }

}
