package unit;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.model.IssueInstance;
import org.example.services.JiraServiceCheck;
import org.example.task.CheckTaskCasesInIssueTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.util.IssueInstanceTestModel;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CheckTaskCasesInIssueTaskTest {

    @Mock
    private DelegateExecution execution;
    @Mock
    private JiraServiceCheck jiraServiceCheck;
    @Mock
    private IssueInstance issueInstance;
    @InjectMocks
    private CheckTaskCasesInIssueTask task;

    @Test
    public void givenIssue_whenCheckTestCases_thenReturnTrue () {
        when(issueInstance.getIssue()).thenReturn(new IssueInstanceTestModel().getIssue());
        when(jiraServiceCheck.checkIfTestCasesExist(any())).thenReturn(true);
        when(execution.getVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT)).thenReturn(true);
        task.execute(execution);
        verify(execution).setVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT, true);
    }

    @Test
    public void givenIssue_whenCheckTestCases_thenReturnFalse () {
        when(issueInstance.getIssue()).thenReturn(new IssueInstanceTestModel().getIssue());
        when(jiraServiceCheck.checkIfTestCasesExist(any())).thenReturn(false);
        when(execution.getVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT)).thenReturn(false);
        task.execute(execution);
        verify(execution).setVariable(ProcessEnv.ARE_NEED_TASKS_PRESENT, false);
    }
}
