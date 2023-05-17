package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.LinkIssuesInput;
import org.example.configuration.UserConfiguration;
import org.example.repository.interfaces.JiraRepositoryUpdate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Repository
@Profile({"dev"})
public class JiraRepositoryUpdateImpl implements JiraRepositoryUpdate {

    private JiraRestClient jiraRestClient;

    private final UserConfiguration userConfiguration;

    public JiraRepositoryUpdateImpl(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    @Override
    public BasicIssue clone(IssueInput issueInput) {
        jiraRestClient = userConfiguration.getJiraRestClient();
        try {
            return jiraRestClient.getIssueClient().createIssue(issueInput).get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateClone(String key, IssueInput issueInput) {
        try {
            jiraRestClient.getIssueClient().updateIssue(key, issueInput).get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setLinkToIssue(String keyFrom, String keyTo, String linkType) {
        jiraRestClient = userConfiguration.getJiraRestClient();
        LinkIssuesInput linkIssuesInput = new LinkIssuesInput(keyFrom, keyTo, linkType);
        try {
            jiraRestClient.getIssueClient()
                    .linkIssue(linkIssuesInput)
                    .get(10, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException  e) {
            throw new RuntimeException(e);
        }
        return keyFrom;
    }

}
