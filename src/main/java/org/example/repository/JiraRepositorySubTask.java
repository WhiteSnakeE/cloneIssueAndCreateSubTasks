package org.example.repository;

import com.atlassian.jira.rest.client.api.domain.input.IssueInput;

public interface JiraRepositorySubTask {

     String createSubTask(IssueInput subtask);
}
