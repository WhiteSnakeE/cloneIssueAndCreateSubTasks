package service;

import org.example.repository.JiraRepositoryRestUpdate;
import org.example.services.JiraServiceClone;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryIssueMock;
import service.util.JiraRepositoryUpdateMock;

@ExtendWith(MockitoExtension.class)
public class JiraServiceCloneTest {

    JiraServiceClone jiraServiceClone = new JiraServiceClone(new JiraRepositoryIssueMock(), new JiraRepositoryUpdateMock());

}
