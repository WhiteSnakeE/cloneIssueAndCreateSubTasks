package org.example.services;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import org.camunda.bpm.engine.delegate.BpmnError;
import org.example.repository.JiraRepository;
import org.springframework.stereotype.Service;


@Service
public class JiraService {
    private static String jql = "key = ";
    private final JiraRepository jiraRepository;

    public JiraService (JiraRepository jiraRepository) {
        this.jiraRepository = jiraRepository;
    }

    public String checkIfIssueExist (String key) {
        jiraRepository.isProjectExist(jql + key).getIssues().iterator().next().getKey();
        return key;
    }

}
