package service;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import org.example.services.JiraServiceSubTaskCreator;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import service.util.JiraRepositorySubtaskCreatorMock;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class JiraServiceSubTaskCreatorTest {
    JiraServiceSubTaskCreator jiraServiceSubTaskCreator = new JiraServiceSubTaskCreator(new JiraRepositorySubtaskCreatorMock());
@Test
    public void createSubTask () {
        IssueLinkType issueLinkType = new IssueLinkType("Relates","relates to", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-18",null,issueLinkType);
        String projectKey = "FIXBIT-18";

       String value = jiraServiceSubTaskCreator.createSubTask(issueLink,projectKey);
    Assertions.assertEquals("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")), value);

    }
}
