package org.example.services;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.repository.JiraRepository;
import org.springframework.stereotype.Service;

import java.util.Objects;


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

    public boolean checkIfTestCasesExist () {
        Issue issue = jiraRepository.getNeedIssue();
        if (issue.getIssueLinks() == null) {
            return false;
        }
        
        for (IssueLink issueLink : Objects.requireNonNull(issue.getIssueLinks())) {
            if (!issueLink.getIssueLinkType().getName().equals("Cloners")) {
                return true;
            }
        }
        return false;
    }
}
