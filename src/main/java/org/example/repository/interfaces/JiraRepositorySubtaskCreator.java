package org.example.repository.interfaces;

import com.atlassian.jira.rest.client.api.domain.input.IssueInput;

public interface JiraRepositorySubtaskCreator {

     String createSubTask(IssueInput subtask);
}
