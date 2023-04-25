package org.example.services;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.repository.interfaces.JiraRepositoryCheck;
import org.example.repository.interfaces.JiraRepositoryIssue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class JiraServiceCheck {
    private static final String jql = "key = ";
    private final JiraRepositoryCheck jiraRepositoryCheck;

    private final JiraRepositoryIssue jiraRepositoryIssue;

    public JiraServiceCheck (JiraRepositoryCheck jiraRepositoryCheck,
            @Qualifier("jiraRepositoryIssueImpl") JiraRepositoryIssue jiraRepositoryIssue) {
        this.jiraRepositoryCheck = jiraRepositoryCheck;
        this.jiraRepositoryIssue = jiraRepositoryIssue;
    }

    public String checkIfIssueExist (String key) {
        return jiraRepositoryCheck.isProjectExist(jql + key).getIssues().iterator().next().getKey();
    }

    public boolean checkIfTestCasesExist () {
        Issue issue = jiraRepositoryIssue.getIssue();
        List<IssueLink> issueLinks = new ArrayList<>();
        if (issue.getIssueLinks() == null) {
            return false;
        }
        for (IssueLink issueLink : Objects.requireNonNull(issue.getIssueLinks())) {
            if (!issueLink.getIssueLinkType().getName().equals("Cloners")) {
                issueLinks.add(issueLink);
            }
        }
        jiraRepositoryIssue.setIssueLinks(issueLinks);
        return !issueLinks.isEmpty();
    }

    public List<IssueLink> collectIssueLinks () {
        return jiraRepositoryIssue.getIssueLinks();
    }


}
