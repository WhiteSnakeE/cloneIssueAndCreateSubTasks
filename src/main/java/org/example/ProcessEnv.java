package org.example;


import org.camunda.bpm.engine.delegate.VariableScope;
import org.example.model.IssueLinkModel;

import java.util.List;

public class ProcessEnv {
    public static final String ISSUE_KEY = "issueKey";

    public static final String ARE_NEED_TASKS_PRESENT = "tasksArePresent";

    public static final String JIRA_TASK_CASES = "taskCases";
    public static final String TASK_CASE = "taskCase";

    public static final String CLONE_KEY = "cloneKey";

    public static final String SUBTASK_KEY = "subtaskKey";
    private final VariableScope variableScope;

    public ProcessEnv (VariableScope variableScope) {
        this.variableScope = variableScope;
    }

    public String getSubtaskKey () {
        return (String) variableScope.getVariable(SUBTASK_KEY);
    }

    public void setIssueKey (String projectName) {
        variableScope.setVariable(ISSUE_KEY, projectName);
    }

    public void setSubtaskKey (String subtaskKey) {
        variableScope.setVariable(SUBTASK_KEY, subtaskKey);
    }

    public void setCloneKey (String cloneKey) {
        variableScope.setVariable(CLONE_KEY, cloneKey);
    }

    public String getCloneKey () {
        return (String) variableScope.getVariable(CLONE_KEY);
    }

    public void setAreNeedIssuesPresent (boolean areNeedIssueIsPresent) {
        variableScope.setVariable(ARE_NEED_TASKS_PRESENT, areNeedIssueIsPresent);
    }

    public boolean getAreNeedIssuesPresent () {
        return (boolean) variableScope.getVariable(ARE_NEED_TASKS_PRESENT);
    }

    public IssueLinkModel getTaskCase () {
        return (IssueLinkModel) variableScope.getVariable(TASK_CASE);
    }

    public void setTaskCases (List<IssueLinkModel> taskCases) {
        variableScope.setVariable(JIRA_TASK_CASES, taskCases);
    }
}
