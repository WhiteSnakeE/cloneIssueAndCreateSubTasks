package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import lombok.extern.slf4j.Slf4j;
import org.example.model.IssueInstance;
import org.example.repository.interfaces.JiraRepositoryIssue;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

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
    public List<IssueLink> getIssueLinks () {
       return issueInstance.getIssueLinkList();
    }

    @Override
    public void setIssueLinks (List<IssueLink> issueLinks) {
            issueInstance.setIssueLinkList(issueLinks);
    }
}
