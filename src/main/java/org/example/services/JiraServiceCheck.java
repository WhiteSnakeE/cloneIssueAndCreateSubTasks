package org.example.services;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.repository.JiraRepositoryCheck;
import org.example.repository.JiraRepositoryIssue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;


@Service
public class JiraServiceCheck {
    private static final String jql = "key = ";
    private final JiraRepositoryCheck jiraRepositoryCheck;

    private final JiraRepositoryIssue jiraRepositoryIssue;

    public JiraServiceCheck (JiraRepositoryCheck jiraRepositoryCheck,
            @Qualifier("jiraRepositoryRestIssue") JiraRepositoryIssue jiraRepositoryIssue) {
        this.jiraRepositoryCheck = jiraRepositoryCheck;
        this.jiraRepositoryIssue = jiraRepositoryIssue;
    }

    public String checkIfIssueExist (String key) {
        return jiraRepositoryCheck.isProjectExist(jql + key).getIssues().iterator().next().getKey();
    }

    public boolean checkIfTestCasesExist () {
        Issue issue = jiraRepositoryIssue.getIssue();
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
