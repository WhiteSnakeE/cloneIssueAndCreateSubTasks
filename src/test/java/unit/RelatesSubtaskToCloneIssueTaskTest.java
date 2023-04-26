package unit;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.model.IssueLinkModel;
import org.example.services.JiraServiceClone;
import org.example.services.converter.IssueLinkConverter;
import org.example.task.RelatesSubtaskToCloneIssueTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class RelatesSubtaskToCloneIssueTaskTest {
    @Mock
    private DelegateExecution execution;
    @Mock
    private JiraServiceClone jiraServiceClone;

    @InjectMocks
    private RelatesSubtaskToCloneIssueTask task;
    @Test
    public void givenSubtask_whenWeRelatesToClone_CloneHaveToHasSubtask(){
        IssueLinkType issueLinkType = new IssueLinkType("name","descriprion", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-1000", URI.create("someUri"),issueLinkType);
        List<IssueLinkModel> issueLinkModels = new IssueLinkConverter().convertToIssueLinkModel(List.of(issueLink));
        when(jiraServiceClone.setSubtaskLinkToClone(issueLink,null,null)).thenReturn("FIXBIT-1000");
        when(execution.getVariable(ProcessEnv.TASK_CASE)).thenReturn(issueLinkModels.get(0));
        task.execute(execution);
        verify(execution).setVariable(ProcessEnv.SUBTASK_KEY,"FIXBIT-1000");
    }
}
