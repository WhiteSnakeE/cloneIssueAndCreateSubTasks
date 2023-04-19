package org.example;

import org.camunda.bpm.engine.delegate.VariableScope;

public class ProcessEnv {
    public static final String ISSUE_KEY = "issueKey";

    public static final String ARE_NEED_TASKS_PRESENT ="tasksArePresent";

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

    public void setAreNeedIssuesPresent(boolean areNeedIssueIsPresent) {
        variableScope.setVariable(ARE_NEED_TASKS_PRESENT, areNeedIssueIsPresent);
    }

    public boolean getAreNeedIssuesPresent(){
        return (boolean) variableScope.getVariable(ARE_NEED_TASKS_PRESENT);
    }
}
