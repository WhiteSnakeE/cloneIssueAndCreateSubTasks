package org.example.configuration;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class UserConfiguration {

    public JiraRestClient getJiraRestClient() {
        return jiraRestClient;
    }

    private JiraRestClient jiraRestClient;

    @Value("${jira.url}")
    private String url;

    public void createJiraRestClient(String user,String password){
        jiraRestClient =  new JiraConfiguration().jiraRestClient(user, password, url);
    }

    public void close(){
        try {
            jiraRestClient.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
