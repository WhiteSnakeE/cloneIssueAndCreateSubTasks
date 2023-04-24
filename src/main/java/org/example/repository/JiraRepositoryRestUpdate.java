package org.example.repository;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.LinkIssuesInput;
import lombok.extern.slf4j.Slf4j;
import org.example.model.IssueInstance;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryRestUpdate implements JiraRepositoryUpdate {


    private final JiraRestClient jiraRestClient;

    private final IssueInstance instance;

    public JiraRepositoryRestUpdate (JiraRestClient jiraRestClient, IssueInstance instance) {
        this.jiraRestClient = jiraRestClient;
        this.instance = instance;
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

    @Override
    public void setSubtaskLinkToCLone (IssueLink issueLink) {
        LinkIssuesInput linkIssuesInput = new LinkIssuesInput(issueLink.getTargetIssueKey(),instance.getCloneKey(),"Relates");
        jiraRestClient.getIssueClient().linkIssue(linkIssuesInput).claim();
    }
}
