package service;

import org.example.services.JiraServiceCheck;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryMock;

import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
public class JiraServiceCheckTest {

    JiraServiceCheck jiraServiceCheck = new JiraServiceCheck(new JiraRepositoryMock());

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
}
