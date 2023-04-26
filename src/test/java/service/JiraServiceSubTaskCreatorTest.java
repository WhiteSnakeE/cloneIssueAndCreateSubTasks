package service;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import org.example.repository.interfaces.JiraRepositorySubtaskCreator;
import org.example.services.JiraServiceSubTaskCreator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import service.util.JiraRepositoryIssueMock;
import service.util.JiraRepositorySubtaskCreatorMock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JiraServiceSubTaskCreatorTest {
    JiraServiceSubTaskCreator jiraServiceSubTaskCreator = new JiraServiceSubTaskCreator(new JiraRepositorySubtaskCreatorMock(),new JiraRepositoryIssueMock());
@Test
    public void createSubTask () {
        IssueLinkType issueLinkType = new IssueLinkType("Relates","relates to", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-18",null,issueLinkType);

       String value = jiraServiceSubTaskCreator.createSubTask(issueLink);
    Assertions.assertEquals("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")), value);

    }
}
