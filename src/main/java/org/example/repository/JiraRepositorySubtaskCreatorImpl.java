package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import org.example.model.IssueInstance;
import org.example.repository.interfaces.JiraRepositorySubtaskCreator;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Repository
public class JiraRepositorySubtaskCreatorImpl implements JiraRepositorySubtaskCreator {

    private final JiraRestClient jiraRestClient;
    private final IssueInstance instance;

    public JiraRepositorySubtaskCreatorImpl (JiraRestClient jiraRestClient, IssueInstance instance) {
        this.jiraRestClient = jiraRestClient;
        this.instance = instance;
    }

    @Override
    public String createSubTask (IssueInput subtask) {
        String key = null;
        try {
            key = jiraRestClient.getIssueClient().createIssue(subtask).get(2, TimeUnit.SECONDS).getKey();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        instance.setSubtaskKey(key);
        return key;
    }
}
