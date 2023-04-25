package service.util;

import com.atlassian.jira.rest.client.api.domain.*;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import org.example.repository.interfaces.JiraRepositoryUpdate;

import java.net.URI;
import java.util.ArrayList;

public class JiraRepositoryUpdateMock implements JiraRepositoryUpdate {

    private BasicIssue basicIssue;

    private Issue issue;

    public Issue getIssue () {
        return issue;
    }

    @Override
    public BasicIssue clone (IssueInput issueInput) {
        ComplexIssueInputFieldValue complexIssueInputFieldValue = (ComplexIssueInputFieldValue) issueInput.getFields().get("project").getValue();
        String key = complexIssueInputFieldValue.getValuesMap().get("key").toString();
        basicIssue = new BasicIssue(URI.create("https://sytoss.atlassian.net/rest/api/3/issue/14643)"), key, 14105L);
        return basicIssue;
    }

    @Override
    public void updateClone (String key, IssueInput issueInput) {
        issue = new Issue(null, URI.create("https://sytoss.atlassian.net/rest/api/3/issue/14643"), "FIXBIT-18", null, null, null, null, "description", null, null, null, null, null, null, null, null, new ArrayList<>(), new ArrayList<>(), null, null, null, null, null, null, null, null, null, null, null, null, null, null);

    }

    @Override
    public String setLinkToIssue (String keyFrom, String keyTo, String linkType) {
        return null;
    }


}
