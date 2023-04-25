package org.example.task;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.example.ProcessEnv;
import org.example.model.IssueLinkModel;
import org.example.services.JiraServiceCheck;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("CollectTaskCases")
@Slf4j
public class CollectTaskCasesTask implements JavaDelegate {

    private final JiraServiceCheck  jiraServiceCheck;

    public CollectTaskCasesTask (JiraServiceCheck jiraServiceCheck) {
        this.jiraServiceCheck = jiraServiceCheck;
    }

    @Override
    public void execute (DelegateExecution delegateExecution){
        List<IssueLink> issueLinkList = jiraServiceCheck.collectIssueLinks();
        List<IssueLinkModel> issueLinkModels = jiraServiceCheck.convertIssueLinks(issueLinkList);
        ProcessEnv processEnv = new ProcessEnv(delegateExecution);
        processEnv.setTaskCases(issueLinkModels);
        log.info("task cases were collected");

    }
}
