package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.services.JiraServiceCheck;
import org.springframework.stereotype.Component;

@Component("AuthificationCheck")
@Slf4j
public class AuthificationCheckTask implements JavaDelegate {
    private final JiraServiceCheck jiraServiceCheck;

    public AuthificationCheckTask (JiraServiceCheck jiraServiceCheck) {
        this.jiraServiceCheck = jiraServiceCheck;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String user = (String) delegateExecution.getVariable("JiraLogin");
        String password = (String) delegateExecution.getVariable("JiraPassword");
        log.info("UserName is {}", user);
        log.info("Password is {}", password);
        jiraServiceCheck.setUserAndPassword(user,password);

    }
}
