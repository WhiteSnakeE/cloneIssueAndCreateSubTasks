package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.Issue;
import lombok.extern.slf4j.Slf4j;
import org.example.model.IssueInstance;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryRestIssue implements JiraRepositoryIssue {
    private final IssueInstance issueInstance;

    public JiraRepositoryRestIssue (IssueInstance issueInstance) {
        this.issueInstance = issueInstance;
    }

    @Override
    public Issue getIssue () {
        return issueInstance.getIssue();
    }

    @Override
    public String getCloneKey () {
        return issueInstance.getCloneKey();
    }

    @Override
    public void setCloneKey (String cloneKey) {
        issueInstance.setCloneKey(cloneKey);
    }
}
