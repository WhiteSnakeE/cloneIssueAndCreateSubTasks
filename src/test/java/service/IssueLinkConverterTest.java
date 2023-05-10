package service;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import org.example.model.IssueLinkModel;
import org.example.services.converter.IssueLinkConverter;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class IssueLinkConverterTest {

    private final IssueLinkConverter issueLinkConverter = new IssueLinkConverter();

    @Test
    public void convertToIssueLinkModel(){
        List<IssueLink> issueLinkList = new ArrayList<>();
        IssueLinkType issueLinkType = new IssueLinkType("name","descriprion", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-1000", URI.create("someUri"),issueLinkType);
        issueLinkList.add(issueLink);
        List<IssueLinkModel> issueLinkModels = new ArrayList<>();
        IssueLinkModel issueLinkModel = new IssueLinkModel("FIXBIT-1000", URI.create("someUri"),"name","descriprion", IssueLinkType.Direction.OUTBOUND);
        issueLinkModels.add(issueLinkModel);
        List<IssueLinkModel> correctIssueLinkModels = issueLinkConverter.convertToIssueLinkModel(issueLinkList);
        Assertions.assertEquals(correctIssueLinkModels,issueLinkModels);
    }
    @Test
    public void convertToIssueLink(){
        IssueLinkModel issueLinkModel = new IssueLinkModel("FIXBIT-1000", URI.create("someUri"),"name","descriprion", IssueLinkType.Direction.OUTBOUND);
        IssueLinkType issueLinkType = new IssueLinkType("name","descriprion", IssueLinkType.Direction.OUTBOUND);
        IssueLink issueLink = new IssueLink("FIXBIT-1000", URI.create("someUri"),issueLinkType);
        IssueLink correctIssueLink = issueLinkConverter.convertToIssueLink(issueLinkModel);
        Assertions.assertEquals(correctIssueLink,issueLink);
    }

}
