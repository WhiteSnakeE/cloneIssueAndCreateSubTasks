package repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.RestClientException;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import io.atlassian.util.concurrent.Promise;
import org.example.exeptions.IssueNotExistException;
import org.example.model.IssueInstance;
import org.example.repository.JiraRepositoryCheckImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class JiraRepositoryCheckImplTest {
    @Mock
    private JiraRestClient jiraRestClient;
    @Mock
    private SearchRestClient searchRestClient;
    @Mock
    private Promise<SearchResult> promise;
    @Mock
    private IssueInstance issueInstance;

    private JiraRepositoryCheckImpl jiraRepositoryCheckImpl;

    @Before
    public void setup(){
        when(jiraRestClient.getSearchClient()).thenReturn(searchRestClient);
        jiraRepositoryCheckImpl = new JiraRepositoryCheckImpl(jiraRestClient,issueInstance);
    }

    @Test
    public void isProjectExist () throws ExecutionException, InterruptedException, TimeoutException {
        Issue issue = new Issue("summary", URI.create("https://sytoss.atlassian.net/rest/api/3/issue/14643"), "FIXBIT-18", null, null, null, null, "description", null, null, null, null, null, null, null, null, new ArrayList<>(), new ArrayList<>(), null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        Iterable<Issue> issues = List.of(issue);
        SearchResult searchResult = new SearchResult(0, 1, 1, issues);
        when(searchRestClient.searchJql(anyString(), anyInt(), anyInt(), anyObject())).thenReturn(promise);
        when(promise.get(2, TimeUnit.SECONDS)).thenReturn(searchResult);
        jiraRepositoryCheckImpl.isProjectExist("hello");
        verify(issueInstance).setIssue(issue);
       Assertions.assertEquals( searchResult,jiraRepositoryCheckImpl.isProjectExist("hello"));
    }
    @Test
    public void isProjectExistIssueNotExistException () throws ExecutionException, InterruptedException, TimeoutException {
        when(searchRestClient.searchJql(anyString(), anyInt(), anyInt(), anyObject())).thenReturn(promise);
        when(promise.get(2, TimeUnit.SECONDS)).thenThrow(RestClientException.class);
        Assertions.assertThrows(IssueNotExistException.class, () -> {
            jiraRepositoryCheckImpl.isProjectExist("hello");
        });
    }

    @Test
    public void isProjectExistRuntimeException () throws ExecutionException, InterruptedException, TimeoutException {
        when(searchRestClient.searchJql(anyString(), anyInt(), anyInt(), anyObject())).thenReturn(promise);
        when(promise.get(2, TimeUnit.SECONDS)).thenThrow(TimeoutException.class);
        Assertions.assertThrows(RuntimeException.class, () -> {
            jiraRepositoryCheckImpl.isProjectExist("hello");
        });
    }
}
