package org.example.configuration;

import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;

@Component
@Slf4j
public class JiraConfiguration {

    private JiraRestClient jiraRestClient;

    public JiraRestClient getJiraRestClient() {
        return jiraRestClient;
    }

    @Value("${jira.url}")
    private String url;

    public void createJiraRestClient(String user, String password) {
        jiraRestClient = jiraRestClient(user, password, url);
    }


    private JiraRestClient jiraRestClient(String user, String password, String url) {

            AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            AuthenticationHandler authenticationHandler = new BasicHttpAuthenticationHandler(user, password);
            return factory.createWithAuthenticationHandler(URI.create(url), authenticationHandler);


    }

    public void close() {
        try {
            jiraRestClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
