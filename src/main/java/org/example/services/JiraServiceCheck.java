package org.example.services;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.repository.JiraRepositoryCheck;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class JiraServiceCheck {
    private static String jql = "key = ";
    private final JiraRepositoryCheck jiraRepositoryCheck;

    public JiraServiceCheck (JiraRepositoryCheck jiraRepositoryCheck) {
        this.jiraRepositoryCheck = jiraRepositoryCheck;
    }

    public String checkIfIssueExist (String key) {
        return jiraRepositoryCheck.isProjectExist(jql + key).getIssues().iterator().next().getKey();
    }

    public boolean checkIfTestCasesExist () {
        Issue issue = jiraRepositoryCheck.getNeedIssue();
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
