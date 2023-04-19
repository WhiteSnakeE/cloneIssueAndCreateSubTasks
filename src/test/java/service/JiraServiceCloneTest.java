package service;

import org.example.services.JiraServiceClone;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import service.util.JiraRepositoryCloneMock;

@ExtendWith(MockitoExtension.class)
public class JiraServiceCloneTest {

    JiraServiceClone jiraServiceClone = new JiraServiceClone(new JiraRepositoryCloneMock());

}
