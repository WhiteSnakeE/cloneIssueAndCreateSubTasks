package unit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.task.FindIssueByProjectKeyTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FindIssueByProjectKeyTest {
    @Mock
    private DelegateExecution execution;


    @InjectMocks
    private FindIssueByProjectKeyTask task;

    @Test
    public void givenIssueKey_whenStartMessageEvent_thenCheckOutput () {

        when(execution.getVariable("IssueKey")).thenReturn("FIXBIT-18");

        task.execute(execution);

        verify(execution).setVariable(ProcessEnv.ISSUE_KEY, "FIXBIT-18");

    }
}
