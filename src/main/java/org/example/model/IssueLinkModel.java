package org.example.model;

import com.atlassian.jira.rest.client.api.domain.IssueLinkType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.net.URI;
@Data
@Builder
@AllArgsConstructor
public class IssueLinkModel implements Serializable {
    private String targetIssueKey;
    private  URI targetIssueUri;
    private  String name;
    private  String description;

    private IssueLinkType.Direction direction;

}
