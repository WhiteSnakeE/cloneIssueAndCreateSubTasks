package service;

import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;

import org.example.services.JiraServiceClone;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryIssueMock;
import service.util.JiraRepositoryUpdateMock;

import java.util.Base64;

@ExtendWith(MockitoExtension.class)
public class JiraServiceCloneTest {

    private JiraRepositoryIssueMock jiraRepositoryIssueMock = new JiraRepositoryIssueMock();

    private JiraRepositoryUpdateMock jiraRepositoryUpdateMock = new JiraRepositoryUpdateMock();

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
        IssueInput issueInput = jiraServiceClone.updateNotNullIssueFields(jiraRepositoryIssueMock.getIssue());
        ComplexIssueInputFieldValue complexIssueInputFieldValue = (ComplexIssueInputFieldValue) issueInput.getField("assignee").getValue();
        String name = complexIssueInputFieldValue.getValuesMap().get("name").toString();
        Assertions.assertEquals(name,jiraRepositoryIssueMock.getIssue().getAssignee().getName());

    }

}
