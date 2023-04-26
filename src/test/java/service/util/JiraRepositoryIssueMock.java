package service.util;

import com.atlassian.jira.rest.client.api.domain.*;
import org.example.model.IssueInstance;
import org.example.repository.interfaces.JiraRepositoryIssue;

import java.net.URI;
import java.util.*;

public class JiraRepositoryIssueMock implements JiraRepositoryIssue {

    @Override
    public Issue getIssue () {
        IssueLinkType issueLinkType = new IssueLinkType("Relates","relates to", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-18",null,issueLinkType);
        BasicPriority basicPriority = new BasicPriority(URI.create("https://sytoss.atlassian.net/rest/api/3/priority/3"),3L,"Medium");
        IssueType issueType = new IssueType(URI.create("https://sytoss.atlassian.net/rest/api/3/issuetype/10109"),10109L,"Test Suite",false,"",URI.create("https://sytoss.atlassian.net/rest/api/2/universal_avatar/view/type/issuetype/avatar/10300?size=medium"));
        BasicProject basicProject = new BasicProject(URI.create("https://sytoss.atlassian.net/rest/api/3/project/10036"),"FIXBIT",null,null);
        Attachment attachment = new Attachment(null,null,null,null,1,null,null,null);
        Map<String, URI> avatarUris = new HashMap<>();
        avatarUris.put("48x48", URI.create("https://secure.gravatar.com/avatar/6dee5b15ee2e8726511709c8a252e082?d=https%3A%2F%2Favatar-management--avatars.us-west-2.prod.public.atl-paas.net%2Finitials%2FVK-5.png"));
        User user = new User(null, "Vlad", "null", "null", "text@gmail.com", true, null, avatarUris, null);

        return new Issue("summary", URI.create("https://sytoss.atlassian.net/rest/api/3/issue/14643"), "FIXBIT-18", null, basicProject, issueType, null, "description", basicPriority, null, List.of(attachment), null, user, null, null, null,new ArrayList<>() , new ArrayList<>(), null, null, null, null, null, List.of(issueLink), null, null, null, null, null, null, null, null);
    }

    @Override
    public List<IssueLink> getIssueLinks () {

        IssueLinkType issueLinkType = new IssueLinkType("name","descriprion", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-1000", URI.create("someUri"),issueLinkType);
        return List.of(issueLink);
    }

    @Override
    public void setIssueLinks (List<IssueLink> issueLinks) {

    }
}
