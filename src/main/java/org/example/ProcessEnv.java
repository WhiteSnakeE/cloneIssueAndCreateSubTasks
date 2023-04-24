package org.example;

import com.atlassian.jira.rest.client.api.domain.IssueLink;
import org.camunda.bpm.engine.delegate.VariableScope;

import java.util.List;

public class ProcessEnv {
    public static final String ISSUE_KEY = "issueKey";

    public static final String ARE_NEED_TASKS_PRESENT = "tasksArePresent";

    public static final String JIRA_TASK_CASES = "taskCases";
    public static final String TASK_CASE = "tasCase";

    public static final String CLONE_KEY = "cloneKey";
    private final VariableScope variableScope;


    public ProcessEnv (VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public String getIssueKey () {
        return (String) variableScope.getVariable(ISSUE_KEY);
    }

    public void setIssueKey (String projectName) {
        variableScope.setVariable(ISSUE_KEY, projectName);
    }


    public void setCloneKey (String cloneKey) {
        variableScope.setVariable(CLONE_KEY, cloneKey);
    }

    public void setAreNeedIssuesPresent (boolean areNeedIssueIsPresent) {
        variableScope.setVariable(ARE_NEED_TASKS_PRESENT, areNeedIssueIsPresent);
    }

    public boolean getAreNeedIssuesPresent () {
        return (boolean) variableScope.getVariable(ARE_NEED_TASKS_PRESENT);
    }

    public void setTaskCase (IssueLink issueLink) {
        variableScope.setVariable(TASK_CASE, issueLink);
    }

    public IssueLink getTaskCase () {
        return (IssueLink) variableScope.getVariable(TASK_CASE);
    }

    public void setTaskCases (List<IssueLink> taskCases) {
        variableScope.setVariable(JIRA_TASK_CASES, taskCases);
    }
}
