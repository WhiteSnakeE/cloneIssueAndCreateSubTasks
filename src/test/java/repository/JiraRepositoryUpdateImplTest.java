package repository;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import io.atlassian.util.concurrent.Promise;
import org.example.configuration.JiraConfiguration;
import org.example.repository.JiraRepositoryUpdateImpl;
import org.example.services.LinkTypeEnum;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class JiraRepositoryUpdateImplTest {



    @Mock
    private IssueRestClient issueRestClient;

    @Mock
    private Promise<BasicIssue> basicIssuePromise;

    @Mock
    private Promise<Void> voidPromise;

    @Mock
    private JiraConfiguration jiraConfiguration;
    @Mock
    private JiraRestClient jiraRestClient;

    @InjectMocks
    private JiraRepositoryUpdateImpl jiraRepositoryUpdate;

    @Test
    public void cloneIssue() throws ExecutionException, InterruptedException, TimeoutException {
        BasicIssue basicIssue = new BasicIssue(URI.create("SELF"), "KEY", 123L);
        IssueInput issueInput = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
        when(jiraConfiguration.getJiraRestClient()).thenReturn(jiraRestClient);
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        when(issueRestClient.createIssue(issueInput)).thenReturn(basicIssuePromise);
        when(basicIssuePromise.get(10, TimeUnit.SECONDS)).thenReturn(basicIssue);
        jiraRepositoryUpdate.clone(issueInput);
        Assertions.assertEquals(basicIssue, jiraRepositoryUpdate.clone(issueInput));
    }


    @Test
    public void cloneIssueException() throws ExecutionException, InterruptedException, TimeoutException {
        IssueInput issueInput = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
        lenient().when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        lenient().when(issueRestClient.createIssue(issueInput)).thenReturn(basicIssuePromise);
        lenient().when(basicIssuePromise.get(10, TimeUnit.SECONDS)).thenThrow(TimeoutException.class);
        Assertions.assertThrows(RuntimeException.class, () -> {
            jiraRepositoryUpdate.clone(issueInput);
        });
        jiraConfiguration.close();
    }


    @Test
    public void updateClone() throws ExecutionException, InterruptedException, TimeoutException {
        IssueInput issueInput = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
        when(jiraConfiguration.getJiraRestClient()).thenReturn(jiraRestClient);
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        when(issueRestClient.updateIssue("KEY-20", issueInput)).thenReturn(voidPromise);
        jiraRepositoryUpdate.updateClone("KEY-20", issueInput);
        verify(issueRestClient.updateIssue("KEY-20", issueInput)).get(10, TimeUnit.SECONDS);
        jiraConfiguration.close();
    }

    @Test
    public void updateCloneException() throws ExecutionException, InterruptedException, TimeoutException {
        IssueInput issueInput = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
        lenient().when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        lenient().when(issueRestClient.updateIssue("KEY-20", issueInput)).thenReturn(voidPromise);
        lenient().when(voidPromise.get(10, TimeUnit.SECONDS)).thenThrow(TimeoutException.class);
        Assertions.assertThrows(RuntimeException.class, () -> {
            jiraRepositoryUpdate.updateClone("KEY-20", issueInput);
        });
        jiraConfiguration.close();
    }

    @Test
    public void setLinkToIssueException() {
        lenient().when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        Assertions.assertThrows(RuntimeException.class, () -> {
            jiraRepositoryUpdate.setLinkToIssue("KEY-19", "KEY-20", LinkTypeEnum.RELATES.linkType);
        });
        jiraConfiguration.close();
    }
}
