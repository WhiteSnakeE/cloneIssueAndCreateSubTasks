package unit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.services.JiraServiceClone;
import org.example.task.CloneIssueTask;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CloneIssueTaskTest {

    @Mock
    private DelegateExecution execution;
    @Mock
    private JiraServiceClone jiraServiceClone;
    @InjectMocks
    private CloneIssueTask cloneIssueTask;

    @Test
    public void givenIssue_whenCloneIssue_thenReturnIssueKey () {
        when(jiraServiceClone.cloneIssue()).thenReturn("FIXBIT-100");
        cloneIssueTask.execute(execution);
        verify(execution).setVariable(ProcessEnv.CLONE_KEY, "FIXBIT-100");
    }
}
