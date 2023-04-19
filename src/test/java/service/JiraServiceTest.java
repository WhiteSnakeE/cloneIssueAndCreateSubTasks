package service;

import org.example.repository.JiraRepository;
import org.example.services.JiraService;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryMock;

@ExtendWith(MockitoExtension.class)
public class JiraServiceTest {

    private static String jql = "key = ";

    JiraService jiraService = new JiraService(new JiraRepositoryMock());

    @Test
    public void checkIfIssueExistTest () {
        String key = jiraService.checkIfIssueExist("FIXBIT-18");
        Assertions.assertEquals(key, "FIXBIT-18");


    }
}
