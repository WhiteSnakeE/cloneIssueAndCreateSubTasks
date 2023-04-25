package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.Issue;

public interface JiraRepositoryIssue {

    Issue getIssue ();

    String getCloneKey();

    void setCloneKey(String cloneKey);

    String getSubtaskKey();

    void setSubtaskKey(String subtaskKey);


}
