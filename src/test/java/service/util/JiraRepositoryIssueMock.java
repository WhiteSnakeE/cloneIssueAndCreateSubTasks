package service.util;

import com.atlassian.jira.rest.client.api.domain.Issue;
import org.example.repository.JiraRepositoryIssue;

public class JiraRepositoryIssueMock implements JiraRepositoryIssue {

    @Override
    public Issue getIssue () {
        return null;
    }

    @Override
    public String getCloneKey () {
        return null;
    }

    @Override
    public void setCloneKey (String cloneKey) {

    }
}
