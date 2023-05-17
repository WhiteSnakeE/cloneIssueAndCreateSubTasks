package org.example.repository.interfaces;

import com.atlassian.jira.rest.client.api.domain.SearchResult;


public interface JiraRepositoryCheck {

    SearchResult isProjectExist(String jql);


    void close();

}
