package service;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;

import org.example.services.JiraServiceClone;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryIssueMock;
import service.util.JiraRepositoryUpdateMock;

@ExtendWith(MockitoExtension.class)
public class JiraServiceCloneTest {

    private final JiraRepositoryIssueMock jiraRepositoryIssueMock = new JiraRepositoryIssueMock();

    private final JiraRepositoryUpdateMock jiraRepositoryUpdateMock = new JiraRepositoryUpdateMock();

    JiraServiceClone jiraServiceClone = new JiraServiceClone(jiraRepositoryIssueMock, jiraRepositoryUpdateMock);

    @Test
    public void cloneIssue () {
        String key = jiraServiceClone.cloneIssue();
        Assertions.assertEquals("FIXBIT",key);
    }

    @Test
    public void createCloneIssue () {
        BasicIssue basicIssue = jiraServiceClone.createCloneIssue(jiraRepositoryIssueMock.getIssue());
        Assertions.assertEquals("FIXBIT", basicIssue.getKey());
    }
    @Test
    public void updateNotNullIssueFields(){
        IssueInput issueInput = jiraServiceClone.updateAssigneeAndAttachment(jiraRepositoryIssueMock.getIssue());
        ComplexIssueInputFieldValue complexIssueInputFieldValue = (ComplexIssueInputFieldValue) issueInput.getField("assignee").getValue();
        String name = complexIssueInputFieldValue.getValuesMap().get("name").toString();
        Assertions.assertEquals(name,jiraRepositoryIssueMock.getIssue().getAssignee().getName());

    }
    @Test
    public void setSubtaskLinkToClone (){
        IssueLinkType issueLinkType = new IssueLinkType("Relates","relates to", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-18",null,issueLinkType);
        String key = jiraServiceClone.setSubtaskLinkToClone(issueLink);
        Assertions.assertEquals("FIXBIT-18",key);
    }

}
