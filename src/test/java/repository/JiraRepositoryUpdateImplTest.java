package repository;

import com.atlassian.jira.rest.client.api.IssueRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.domain.BasicIssue;
import com.atlassian.jira.rest.client.api.domain.Comment;
import com.atlassian.jira.rest.client.api.domain.Visibility;
import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import com.atlassian.jira.rest.client.api.domain.input.IssueInputBuilder;
import com.atlassian.jira.rest.client.api.domain.input.LinkIssuesInput;
import io.atlassian.util.concurrent.Promise;
import org.example.repository.JiraRepositoryUpdateImpl;
import org.example.services.LinkTypeEnum;
import org.joda.time.DateTime;
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
    private JiraRestClient jiraRestClient;

    @Mock
    private IssueRestClient issueRestClient;
    @Mock
    private Promise<BasicIssue> basicIssuePromise;
    @Mock
    private Promise<Void> voidPromise;

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
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        when(issueRestClient.createIssue(issueInput)).thenReturn(basicIssuePromise);
        when(basicIssuePromise.get(5, TimeUnit.SECONDS)).thenReturn(basicIssue);
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
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        when(issueRestClient.createIssue(issueInput)).thenReturn(basicIssuePromise);
        when(basicIssuePromise.get(5, TimeUnit.SECONDS)).thenThrow(TimeoutException.class);
        Assertions.assertThrows(RuntimeException.class, () -> {
            jiraRepositoryUpdate.clone(issueInput);
        });
    }

    @Test
    public void updateClone() throws ExecutionException, InterruptedException, TimeoutException {
        IssueInput issueInput = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        when(issueRestClient.updateIssue("KEY-20", issueInput)).thenReturn(voidPromise);
        jiraRepositoryUpdate.updateClone("KEY-20", issueInput);
        verify(issueRestClient.updateIssue("KEY-20", issueInput)).get(2, TimeUnit.SECONDS);
    }

    @Test
    public void updateCloneException() throws ExecutionException, InterruptedException, TimeoutException {
        IssueInput issueInput = new IssueInputBuilder()
                .setProjectKey("KEY")
                .setSummary("Test Run  " + LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM")))
                .setIssueTypeId(10003L)
                .build();
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        when(issueRestClient.updateIssue("KEY-20", issueInput)).thenReturn(voidPromise);
        when(voidPromise.get(2, TimeUnit.SECONDS)).thenThrow(TimeoutException.class);
        Assertions.assertThrows(RuntimeException.class, () -> {
            jiraRepositoryUpdate.updateClone("KEY-20", issueInput);
        });
    }

//    @Test
//    public void setLinkToIssue() throws ExecutionException, InterruptedException, TimeoutException {
//        LinkIssuesInput linkIssuesInput = new LinkIssuesInput("", "", "");
//        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
//        when(issueRestClient.linkIssue(linkIssuesInput)).thenReturn(voidPromise1);
//        jiraRepositoryUpdate.setLinkToIssue("", "", "");
//
//        Assertions.assertEquals("KEY-19",jiraRepositoryUpdate.setLinkToIssue("", "", ""));
//
//    }

    @Test
    public void setLinkToIssueException() {
        when(jiraRestClient.getIssueClient()).thenReturn(issueRestClient);
        Assertions.assertThrows(RuntimeException.class, () -> {
            jiraRepositoryUpdate.setLinkToIssue("KEY-19", "KEY-20", LinkTypeEnum.RELATES.linkType);
        });
    }
}
