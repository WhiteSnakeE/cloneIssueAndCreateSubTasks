package service;

import org.example.services.JiraService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryMock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class JiraServiceTest {

    JiraService jiraService = new JiraService(new JiraRepositoryMock());

    @Test
    public void checkIfIssueExistTest () {
        String key = jiraService.checkIfIssueExist("FIXBIT-18");
        Assertions.assertEquals(key, "FIXBIT-18");
    }

    @Test
    public void checkIfTestCasesExist () {
        boolean isExist = jiraService.checkIfTestCasesExist();
        Assertions.assertTrue(isExist);
    }
}
