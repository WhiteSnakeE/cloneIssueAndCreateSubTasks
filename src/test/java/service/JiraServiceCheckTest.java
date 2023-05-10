package service;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.model.IssueInstance;
import org.example.services.JiraServiceCheck;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.IssueInstanceTestModel;
import service.util.JiraRepositoryCheckMock;

import java.util.List;

import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class JiraServiceCheckTest {


    private final JiraServiceCheck jiraServiceCheck = new JiraServiceCheck(new JiraRepositoryCheckMock(),mock(IssueInstance.class));

    @Test
    public void checkIfIssueExistTest () {
        String key = jiraServiceCheck.checkIfIssueExist("FIXBIT-18");
        Assertions.assertEquals(key, "FIXBIT-18");
    }

    @Test
    public void checkIfTestCasesExist () {
        boolean isExist = jiraServiceCheck.checkIfTestCasesExist(new IssueInstanceTestModel().getIssue());
        Assertions.assertTrue(isExist);
    }


}
