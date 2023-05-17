package org.example.configuration;

import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.net.URI;


@Profile({"dev"})
@Slf4j
public class JiraConfiguration {


    public JiraRestClient jiraRestClient( String user, String password,
            String url) {
        log.info("USERNAME {}", user);
        log.info("PASSWORD {}", password);
        log.info("URL {}", url);
        AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        AuthenticationHandler authenticationHandler = new BasicHttpAuthenticationHandler(user, password);
        return factory.createWithAuthenticationHandler(URI.create(url), authenticationHandler);
    }
}
