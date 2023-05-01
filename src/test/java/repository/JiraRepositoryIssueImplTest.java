package repository;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.example.model.IssueInstance;
import org.example.repository.JiraRepositoryIssueImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
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
public class JiraRepositoryIssueImplTest {
    @Mock
    private IssueInstance issueInstance;
    @InjectMocks
    private JiraRepositoryIssueImpl jiraRepositoryIssue;

    @Test
    public void getIssue() {
        Issue issue = new Issue("summary", URI.create("https://sytoss.atlassian.net/rest/api/3/issue/14643"), "FIXBIT-18", null, null, null, null, "description", null, null, null, null, null, null, null, null, new ArrayList<>(), new ArrayList<>(), null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        when(issueInstance.getIssue()).thenReturn(issue);
        when(jiraRepositoryIssue.getIssue()).thenReturn(issue);
        jiraRepositoryIssue.getIssue();
        Assertions.assertEquals(issue, jiraRepositoryIssue.getIssue());
    }

    @Test
    public void getIssueLinks() {
        List<IssueLink> issueLinks = new ArrayList<>();
        when(issueInstance.getIssueLinkList()).thenReturn(issueLinks);
        when(jiraRepositoryIssue.getIssueLinks()).thenReturn(issueLinks);
        jiraRepositoryIssue.getIssueLinks();
        Assertions.assertEquals(issueLinks, jiraRepositoryIssue.getIssueLinks());
    }

    @Test
    public void setIssueLinks() {
        List<IssueLink> issueLinks = new ArrayList<>();
        jiraRepositoryIssue.setIssueLinks(issueLinks);
        verify(issueInstance).setIssueLinkList(issueLinks);
    }
}
