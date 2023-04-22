package org.example.repository;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.LinkIssuesInput;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryRestUpdate implements JiraRepositoryUpdate {


    private final JiraRestClient jiraRestClient;

    public JiraRepositoryRestUpdate (JiraRestClient jiraRestClient) {
        this.jiraRestClient = jiraRestClient;
    }

    @Override
    public BasicIssue clone (IssueInput issueInput) {
        return jiraRestClient.getIssueClient().createIssue(issueInput).claim();
    }

    @Override
    public void updateClone (String key, IssueInput issueInput) {
        IssueRestClient issueRestClient = jiraRestClient.getIssueClient();
        issueRestClient.updateIssue(key, issueInput).claim();


    }

    @Override
    public void linkIssue (String keyFrom, String keyTo) {
        LinkIssuesInput linkIssuesInput = new LinkIssuesInput(keyFrom, keyTo, "Cloners");
        jiraRestClient.getIssueClient().linkIssue(linkIssuesInput).claim();
    }
}
