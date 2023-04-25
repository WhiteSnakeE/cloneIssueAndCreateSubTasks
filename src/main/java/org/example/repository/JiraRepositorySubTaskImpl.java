package org.example.repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import org.example.model.IssueInstance;
import org.springframework.stereotype.Repository;

@Repository
public class JiraRepositorySubTaskImpl implements JiraRepositorySubTask {

    private final JiraRestClient jiraRestClient;
    private final IssueInstance instance;

    public JiraRepositorySubTaskImpl (JiraRestClient jiraRestClient, IssueInstance instance) {
        this.jiraRestClient = jiraRestClient;
        this.instance = instance;
    }

    @Override
    public String createSubTask (IssueInput subtask) {
       String key = jiraRestClient.getIssueClient().createIssue(subtask).claim().getKey();
       instance.setSubtaskKey(key);
        return key;
    }
}
