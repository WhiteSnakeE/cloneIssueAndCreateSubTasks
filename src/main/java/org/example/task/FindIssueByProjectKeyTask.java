package org.example.task;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.springframework.stereotype.Component;

@Component("FindIssueByProjectKey")
@Slf4j
public class FindIssueByProjectKeyTask implements JavaDelegate {

    @Override
    public void execute (DelegateExecution delegateExecution)  {
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        String issueKey = (String) delegateExecution.getVariable("IssueKey");
        processEnv.setIssueKey(issueKey);
    }
}
