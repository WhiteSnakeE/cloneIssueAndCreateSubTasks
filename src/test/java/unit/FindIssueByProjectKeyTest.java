package unit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.services.JiraService;
import org.example.task.FindIssueByProjectKeyTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindIssueByProjectKeyTest {
    @Mock
    private DelegateExecution execution;
    @Mock
    private JiraService jiraService;

    @InjectMocks
    private FindIssueByProjectKeyTask task;

    private String projectKey = "FIXBIT-18";

    @Test
    public void givenIssueKey_whenStartMessageEvent_thenCheckOutput () {

        when(execution.getVariable("IssueKey")).thenReturn(projectKey);

        when(jiraService.checkIfIssueExist(projectKey)).thenReturn(projectKey);


        task.execute(execution);

        verify(execution).setVariable(ProcessEnv.ISSUE_KEY, projectKey);

    }
}
