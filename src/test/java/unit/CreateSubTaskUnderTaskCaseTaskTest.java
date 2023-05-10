package unit;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.model.IssueInstance;
import org.example.model.IssueLinkModel;
import org.example.services.JiraServiceSubTaskCreator;
import org.example.services.converter.IssueLinkConverter;
import org.example.task.CreateSubTaskUnderTaskCaseTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import service.util.IssueInstanceTestModel;

import java.net.URI;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CreateSubTaskUnderTaskCaseTaskTest {
    @Mock
    private JiraServiceSubTaskCreator jiraServiceSubTaskCreator;

    @Mock
    private DelegateExecution execution;
    @Mock
    private IssueLinkConverter issueLinkConverter;

    @Mock
    private IssueInstance issueInstance;

    @InjectMocks
    private CreateSubTaskUnderTaskCaseTask task;

    @Test
    public void givenTaskCases_whenCreateSubTask_theSubtaskMustBeCreated(){
        IssueLinkType issueLinkType = new IssueLinkType("name","descriprion", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-1000", URI.create("someUri"),issueLinkType);
        when(issueLinkConverter.convertToIssueLink((IssueLinkModel) execution.getVariable(ProcessEnv.TASK_CASE))).thenReturn(issueLink);
        when(issueInstance.getIssue()).thenReturn(new IssueInstanceTestModel().getIssue());
        when(jiraServiceSubTaskCreator.createSubTask(issueLink,"FIXBIT")).thenReturn("FIXBIT-1000");
        task.execute(execution);
        verify(execution).setVariable(ProcessEnv.SUBTASK_KEY, "FIXBIT-1000");
    }
}
