package org.example.services;

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
        return jiraRepository.isProjectExist(jql + key).getIssues().iterator().next().getKey();
    }

}
