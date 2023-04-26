package org.example.model;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IssueInstance {
    private Issue issue;

    private List<IssueLink> issueLinkList;

    public void setIssue(Issue issue){
        this.issue = issue;
    }

    public Issue getIssue () {
        return issue;
    }

    public List<IssueLink> getIssueLinkList () {
        return issueLinkList;
    }

    public void setIssueLinkList (List<IssueLink> issueLinkList) {
        this.issueLinkList = issueLinkList;
    }
}
