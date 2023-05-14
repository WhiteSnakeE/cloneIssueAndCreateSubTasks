package service.util;

import com.atlassian.jira.rest.client.api.domain.input.IssueInput;
import org.example.repository.interfaces.JiraRepositorySubtaskCreator;



public class JiraRepositorySubtaskCreatorMock implements JiraRepositorySubtaskCreator {
    @Override
    public String createSubTask (IssueInput subtask) {

        return subtask.getField("summary").getValue().toString();
    }
}
