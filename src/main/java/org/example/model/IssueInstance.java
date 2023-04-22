package org.example.model;

import com.atlassian.jira.rest.client.api.domain.Issue;
import org.springframework.stereotype.Component;

@Component
public class IssueInstance {
    private Issue issue;

    private String cloneKey;

    public void setIssue(Issue issue){
        this.issue = issue;
    }

    public Issue getIssue () {
        return issue;
    }

    public String getCloneKey () {
        return cloneKey;
    }

    public void setCloneKey (String cloneKey) {
        this.cloneKey = cloneKey;
    }
}
