package org.example.services;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.model.IssueInstance;
import org.example.repository.interfaces.JiraRepositoryCheck;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class JiraServiceCheck {
    private static final String jql = "key = ";
    private final JiraRepositoryCheck jiraRepositoryCheck;

    private final IssueInstance issueInstance;

    public JiraServiceCheck (JiraRepositoryCheck jiraRepositoryCheck, IssueInstance issueInstance) {
        this.jiraRepositoryCheck = jiraRepositoryCheck;
        this.issueInstance = issueInstance;
    }

    public String checkIfIssueExist (String key) {
        return jiraRepositoryCheck.isProjectExist(jql + key).getIssues().iterator().next().getKey();
    }

    public boolean checkIfTestCasesExist (Issue issue) {
        List<IssueLink> issueLinks = new ArrayList<>();
        if (issue.getIssueLinks() == null) {
            return false;
        }
        for (IssueLink issueLink : Objects.requireNonNull(issue.getIssueLinks())) {
            if (!issueLink.getIssueLinkType().getName().equals("Cloners")) {
                issueLinks.add(issueLink);
            }
        }
        issueInstance.setIssueLinkList(issueLinks);
        return !issueLinks.isEmpty();
    }

}
