package configuration;

import com.atlassian.jira.rest.client.api.AuthenticationHandler;
import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.auth.BasicHttpAuthenticationHandler;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import java.net.URI;
@TestConfiguration
public class JiraConfigurationTest {

    @Bean
    public JiraRestClient jiraRestClient(
            @Value("vlad") String jiraUser,
            @Value("123") String jiraPassword,
            @Value("https://some.url.net") String url) {
        AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
        AuthenticationHandler authenticationHandler = new BasicHttpAuthenticationHandler(jiraUser, jiraPassword);
        return factory.createWithAuthenticationHandler(URI.create(url), authenticationHandler);

    }
}
