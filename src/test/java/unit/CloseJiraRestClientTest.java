package unit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.repository.interfaces.JiraRepositoryCheck;
import org.example.task.CloneIssueTask;
import org.example.task.CloseJiraRestClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.util.IssueInstanceTestModel;

import static org.mockito.Mockito.*;
@RunWith(MockitoJUnitRunner.class)
public class CloseJiraRestClientTest {

    @Mock
    private DelegateExecution execution;

    @Mock
    private JiraRepositoryCheck jiraRepositoryCheck;

    @InjectMocks
    private CloseJiraRestClient closeJiraRestClient;

    @Test
    public void givenJiraRestClient_whenClose_thenJiraRestClientMustBeClosed() throws Exception {
        doNothing().when(jiraRepositoryCheck).close();
        closeJiraRestClient.execute(execution);
        verify(jiraRepositoryCheck).close();

    }

}
