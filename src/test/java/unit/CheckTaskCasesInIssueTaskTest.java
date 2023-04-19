package unit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.services.JiraServiceCheck;
import org.example.task.CheckTaskCasesInIssueTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckTaskCasesInIssueTaskTest {

    @Mock
    private DelegateExecution execution;
    @Mock
    private JiraServiceCheck jiraServiceCheck;
    @InjectMocks
    private CheckTaskCasesInIssueTask task;

    @Test
    public void givenIssue_whenCheckTestCases_thenReturnTrue () {
        when(jiraServiceCheck.checkIfTestCasesExist()).thenReturn(true);
        when(execution.getVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT)).thenReturn(true);
        task.execute(execution);
        verify(execution).setVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT, true);
    }

    @Test
    public void givenIssue_whenCheckTestCases_thenReturnFalse () {
        when(jiraServiceCheck.checkIfTestCasesExist()).thenReturn(false);
        when(execution.getVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT)).thenReturn(false);
        task.execute(execution);
        verify(execution).setVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT, false);
    }
}
