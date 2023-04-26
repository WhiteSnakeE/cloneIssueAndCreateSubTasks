package repository;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.SearchRestClient;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import org.example.model.IssueInstance;
import org.example.repository.JiraRepositoryCheckImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

//@RunWith(MockitoJUnitRunner.class)
public class JiraRepositoryCheckImplTest {


    private JiraRestClient jiraRestClient;

    @Mock
    private SearchRestClient searchRestClient = jiraRestClient.getSearchClient();
    @Mock
    private IssueInstance issueInstance;
    @InjectMocks
    private JiraRepositoryCheckImpl jiraRepositoryCheckImpl = new JiraRepositoryCheckImpl(jiraRestClient, issueInstance);

    //@Test
    public void isProjectExist () throws ExecutionException, InterruptedException, TimeoutException {
        Issue issue = new Issue("summary", URI.create("https://sytoss.atlassian.net/rest/api/3/issue/14643"), "FIXBIT-18", null, null, null, null, "description", null, null, null, null, null, null, null, null, new ArrayList<>(), new ArrayList<>(), null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        Iterable<Issue> issues = List.of(issue);
        SearchResult searchResult = new SearchResult(0, 1, 1, issues);
        when(searchRestClient.searchJql("anyString()", 1, 0, null).get(2, TimeUnit.SECONDS)).thenReturn(searchResult);
        Assertions.assertEquals(jiraRepositoryCheckImpl.isProjectExist(anyString()), searchResult);
    }
}
