package service.util;

import com.atlassian.jira.rest.client.api.domain.Issue;
import org.example.repository.JiraRepositoryClone;

public class JiraRepositoryCloneMock implements JiraRepositoryClone {
    @Override
    public Issue cloneIssue () {
        return null;
    }
}
