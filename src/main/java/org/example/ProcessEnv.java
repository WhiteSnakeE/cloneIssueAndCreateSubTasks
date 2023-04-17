package org.example;

import org.camunda.bpm.engine.delegate.VariableScope;

public class ProcessEnv {
    public static final String ISSUE_KEY = "issueKey";
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
}
