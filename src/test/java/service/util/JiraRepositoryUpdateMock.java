package service.util;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import org.example.repository.JiraRepositoryUpdate;

public class JiraRepositoryUpdateMock implements JiraRepositoryUpdate {
    @Override
    public BasicIssue clone (IssueInput issueInput) {
        return null;
    }

    @Override
    public void updateClone (String key, IssueInput issueInput) {

    }

    @Override
    public void linkIssue (String keyFrom, String keyTo) {

    }
}
