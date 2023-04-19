package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.Issue;
import lombok.extern.slf4j.Slf4j;
import org.example.model.IssueInstance;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryRestClone implements JiraRepositoryClone {
    private final IssueInstance issueInstance;

    public JiraRepositoryRestClone (IssueInstance issueInstance) {
        this.issueInstance = issueInstance;
    }

    @Override
    public Issue cloneIssue () {
        return issueInstance.getIssue();
    }
}
