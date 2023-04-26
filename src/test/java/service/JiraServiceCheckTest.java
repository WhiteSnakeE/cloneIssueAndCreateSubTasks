package service;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.services.JiraServiceCheck;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryCheckMock;
import service.util.JiraRepositoryIssueMock;

import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class JiraServiceCheckTest {

    private final JiraServiceCheck jiraServiceCheck = new JiraServiceCheck(new JiraRepositoryCheckMock(), new JiraRepositoryIssueMock());

    @Test
    public void checkIfIssueExistTest () {
        String key = jiraServiceCheck.checkIfIssueExist("FIXBIT-18");
        Assertions.assertEquals(key, "FIXBIT-18");
    }

    @Test
    public void checkIfTestCasesExist () {
        boolean isExist = jiraServiceCheck.checkIfTestCasesExist();
        Assertions.assertTrue(isExist);
    }

    @Test
    public void checkIfIssueLinksExist(){
        List<IssueLink> issueLinks = jiraServiceCheck.collectIssueLinks();
        Assertions.assertEquals(1,issueLinks.size());
        Assertions.assertEquals("FIXBIT-1000",issueLinks.get(0).getTargetIssueKey());
    }

}
