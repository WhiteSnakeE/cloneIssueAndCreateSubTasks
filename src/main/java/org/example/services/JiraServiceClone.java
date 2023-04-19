package org.example.services;

import org.example.repository.JiraRepositoryClone;
import org.springframework.stereotype.Service;

@Service
public class JiraServiceClone {

    private final JiraRepositoryClone jiraRepositoryClone;

    public JiraServiceClone (JiraRepositoryClone jiraRepositoryClone) {
        this.jiraRepositoryClone = jiraRepositoryClone;
    }
}
