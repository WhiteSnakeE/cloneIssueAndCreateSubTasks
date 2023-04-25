package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import lombok.extern.slf4j.Slf4j;
import org.example.model.IssueInstance;
import org.example.repository.interfaces.JiraRepositoryIssue;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Slf4j
@Repository
@Profile({"dev"})
public class JiraRepositoryIssueImpl implements JiraRepositoryIssue {
    private final IssueInstance issueInstance;

    public JiraRepositoryIssueImpl (IssueInstance issueInstance) {
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

    @Override
    public String getSubtaskKey () {
        return issueInstance.getSubtaskKey();
    }

    @Override
    public void setSubtaskKey (String subtaskKey) {
            issueInstance.setSubtaskKey(subtaskKey);
    }

    @Override
    public List<IssueLink> getIssueLinks () {
       return issueInstance.getIssueLinkList();
    }

    @Override
    public void setIssueLinks (List<IssueLink> issueLinks) {
            issueInstance.setIssueLinkList(issueLinks);
    }
}
