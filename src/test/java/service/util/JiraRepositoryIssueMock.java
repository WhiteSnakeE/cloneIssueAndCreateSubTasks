package service.util;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import org.example.repository.JiraRepositoryIssue;

import java.util.List;

public class JiraRepositoryIssueMock implements JiraRepositoryIssue {

    @Override
    public Issue getIssue () {
        IssueLinkType issueLinkType = new IssueLinkType("Relates","relates to", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-18",null,issueLinkType);
        return new Issue(null, null, "FIXBIT-18", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, List.of(issueLink), null, null, null, null, null, null, null, null);
    }

    @Override
    public String getCloneKey () {
        return null;
    }

    @Override
    public void setCloneKey (String cloneKey) {

    }
}
