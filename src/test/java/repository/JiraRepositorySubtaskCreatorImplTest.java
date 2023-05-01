package repository;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.ComplexIssueInputFieldValue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import io.atlassian.util.concurrent.Promise;
import org.example.repository.JiraRepositorySubtaskCreatorImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JiraRepositorySubtaskCreatorImplTest {
    @Mock
    private JiraRestClient jiraRestClient;
    @Mock
    private IssueRestClient issueRestClient;
    @Mock
    private Promise<BasicIssue> basicIssuePromise;
    @Mock
    private BasicIssue basicIssue;

    @InjectMocks
    private JiraRepositorySubtaskCreatorImpl jiraRepositorySubtaskCreator;

    @Test
    public void createSubTask () throws ExecutionException, InterruptedException, TimeoutException {
        IssueInput subtask = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
       when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
       when(issueRestClient.createIssue(subtask)).thenReturn(basicIssuePromise);
       when(basicIssuePromise.get(2,TimeUnit.SECONDS)).thenReturn(basicIssue);
       when(basicIssue.getKey()).thenReturn("KEY-20");
       jiraRepositorySubtaskCreator.createSubTask(subtask);
       Assertions.assertEquals("KEY-20",jiraRepositorySubtaskCreator.createSubTask(subtask));
    }

    @Test
    public void createSubTaskException () throws ExecutionException, InterruptedException, TimeoutException {
        IssueInput subtask = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        when(issueRestClient.createIssue(subtask)).thenReturn(basicIssuePromise);
        when(basicIssuePromise.get(2,TimeUnit.SECONDS)).thenThrow(TimeoutException.class);
        Assertions.assertThrows(RuntimeException.class,() ->{
            jiraRepositorySubtaskCreator.createSubTask(subtask);
        });
    }
}
