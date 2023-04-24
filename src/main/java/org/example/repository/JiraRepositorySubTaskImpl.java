package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import org.springframework.stereotype.Repository;

@Repository
public class JiraRepositorySubTaskImpl implements JiraRepositorySubTask {

    private final JiraRestClient jiraRestClient;

    public JiraRepositorySubTaskImpl (JiraRestClient jiraRestClient) {
        this.jiraRestClient = jiraRestClient;
    }

    @Override
    public String createSubTask (IssueInput subtask) {
        return jiraRestClient.getIssueClient().createIssue(subtask).claim().getKey();
    }
}
