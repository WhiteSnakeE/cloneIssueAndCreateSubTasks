package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.LinkIssuesInput;
import org.example.repository.interfaces.JiraRepositoryUpdate;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Repository
@Profile({"dev"})
public class JiraRepositoryUpdateImpl implements JiraRepositoryUpdate {

    private final JiraRestClient jiraRestClient;

    public JiraRepositoryUpdateImpl (JiraRestClient jiraRestClient) {
        this.jiraRestClient = jiraRestClient;
    }

    @Override
    public BasicIssue clone (IssueInput issueInput) {
        try {
            return jiraRestClient.getIssueClient().createIssue(issueInput).get(5, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateClone (String key, IssueInput issueInput) {
        try {
            jiraRestClient.getIssueClient().updateIssue(key, issueInput).get(2,TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String setLinkToIssue (String keyFrom, String keyTo, String linkType) {
        LinkIssuesInput linkIssuesInput = new LinkIssuesInput(keyFrom, keyTo, linkType);
        try {
            jiraRestClient.getIssueClient()
                    .linkIssue(linkIssuesInput)
                    .get(2,TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
        return keyFrom;
    }

}
