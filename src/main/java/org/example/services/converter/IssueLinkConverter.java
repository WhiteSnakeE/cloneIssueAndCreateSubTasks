package org.example.services.converter;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import org.example.model.IssueLinkModel;

import java.util.ArrayList;
import java.util.List;

public class IssueLinkConverter {
    public List<IssueLinkModel> convertToIssueLinkModel (List<IssueLink> issueLinkList) {
        List<IssueLinkModel> issueLinkModels = new ArrayList<>();
        for (IssueLink issueLink : issueLinkList) {
            issueLinkModels.add(IssueLinkModel.builder()
                    .name(issueLink.getIssueLinkType().getName())
                    .description(issueLink.getIssueLinkType().getDescription())
                    .targetIssueKey(issueLink.getTargetIssueKey())
                    .targetIssueUri(issueLink.getTargetIssueUri())
                    .direction(issueLink.getIssueLinkType().getDirection())
                    .build());
        }
        return issueLinkModels;
    }
    public IssueLink convertToIssueLink (IssueLinkModel issueLinkModel) {
        IssueLinkType issueLinkType = new IssueLinkType(issueLinkModel.getName(), issueLinkModel.getDescription(), issueLinkModel.getDirection());
        return new IssueLink(issueLinkModel.getTargetIssueKey(), issueLinkModel.getTargetIssueUri(), issueLinkType);


    }

}
