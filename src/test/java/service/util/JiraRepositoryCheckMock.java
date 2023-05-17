package service.util;

import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import org.example.repository.interfaces.JiraRepositoryCheck;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
@Repository
@Profile({"dev"})
public class JiraRepositoryCheckMock implements JiraRepositoryCheck {

    public String name;


    @Override
    public SearchResult isProjectExist (String jql) {
        List<Issue> issueList = new ArrayList<>();
        Issue needIssue = new Issue(null, null, "FIXBIT-18", null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null);
        issueList.add(needIssue);
        return new SearchResult(0, 1, 1, issueList);

    }

    @Override
    public void close() {

    }


}
