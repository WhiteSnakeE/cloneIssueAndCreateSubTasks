package unit;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.example.ProcessEnv;
import org.example.model.IssueInstance;
import org.example.model.IssueLinkModel;
import org.example.services.converter.IssueLinkConverter;
import org.example.task.CollectTaskCasesTask;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CollectTaskCasesTaskTest {

    @Mock
    private DelegateExecution execution;
    @Mock
    private IssueInstance issueInstance;
    @Mock
    private IssueLinkConverter issueLinkConverter;
    @InjectMocks
    private CollectTaskCasesTask collectTaskCasesTask;

    @Test
    public void givenCollectedIssueLinks_whenWeConvertThem_thenSetIssueLinkModelsToProcessEnv(){

        List<IssueLink> issueLinkList = new ArrayList<>();
        IssueLinkType issueLinkType = new IssueLinkType("name","descriprion", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-1000", URI.create("someUri"),issueLinkType);
        IssueLinkModel issueLinkModel = new IssueLinkModel("FIXBIT-1000",URI.create("someUri"),"name","descriprion",IssueLinkType.Direction.OUTBOUND);
        List<IssueLinkModel> issueLinkModels = new ArrayList<>();
        issueLinkModels.add(issueLinkModel);
        issueLinkList.add(issueLink);
        when(issueInstance.getIssueLinkList()).thenReturn(issueLinkList);
        when(issueLinkConverter.convertToIssueLinkModel(issueLinkList)).thenReturn(issueLinkModels);
        collectTaskCasesTask.execute(execution);
        verify(execution).setVariable(ProcessEnv.JIRA_TASK_CASES, issueLinkModels);

    }
}
