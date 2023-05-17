package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import org.example.configuration.UserConfiguration;
import org.example.repository.interfaces.JiraRepositorySubtaskCreator;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Repository
public class JiraRepositorySubtaskCreatorImpl implements JiraRepositorySubtaskCreator {


    private final UserConfiguration userConfiguration;

    public JiraRepositorySubtaskCreatorImpl(UserConfiguration userConfiguration) {
        this.userConfiguration = userConfiguration;
    }

    @Override
    public String createSubTask(IssueInput subtask) {
        String key;
        JiraRestClient jiraRestClient  = userConfiguration.getJiraRestClient();
        try {
            key = jiraRestClient.getIssueClient().createIssue(subtask).get(10, TimeUnit.SECONDS).getKey();
        } catch (InterruptedException | TimeoutException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        return key;
    }


}
