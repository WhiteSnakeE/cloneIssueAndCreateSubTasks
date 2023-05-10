package flow;

import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.ProcessEngines;
import org.camunda.bpm.engine.delegate.DelegateExecution;

import org.camunda.bpm.engine.test.Deployment;
import org.camunda.bpm.engine.test.ProcessEngineRule;
import org.camunda.bpm.engine.test.mock.Mocks;
import org.camunda.bpm.extension.process_test_coverage.junit.rules.TestCoverageProcessEngineRuleBuilder;
import org.camunda.bpm.scenario.ProcessScenario;
import org.camunda.bpm.scenario.Scenario;
import org.camunda.bpm.scenario.delegate.TaskDelegate;
import org.example.model.IssueInstance;
import org.example.services.JiraServiceCheck;
import org.example.services.JiraServiceClone;
import org.example.services.JiraServiceSubTaskCreator;
import org.example.task.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
@Deployment(resources = "bpmn/cloneIssueAndCreateSubTasks.bpmn")
public class CloneIssueAndCreateSubtaskFlowTest {
    private final ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();

    @Rule
    public final ProcessEngineRule rule = TestCoverageProcessEngineRuleBuilder.create(engine).build();

    @Mock
    private JiraServiceCheck jiraServiceCheck;
    @Mock
    private JiraServiceClone jiraServiceClone;
    @Mock
    private JiraServiceSubTaskCreator jiraServiceSubTaskCreator;

    @Mock
    private DelegateExecution execution;

    @Before
    public void setup() {
        //  Mocks.register("PrepareIssueKeyInTasklist", new FindIssueByProjectKeyTask(jiraServiceCheck));
        Mocks.register("FindIssueByProjectKey", new FindIssueByProjectKeyTask(jiraServiceCheck));
        Mocks.register("CheckTaskCasesInIssue", new CheckTaskCasesInIssueTask(jiraServiceCheck, mock(IssueInstance.class)));
        Mocks.register("CollectTaskCases", new CollectTaskCasesTask(jiraServiceCheck, issueInstance));
        Mocks.register("CloneIssue", new CloneIssueTask(jiraServiceClone, issueInstance));
        Mocks.register("CreateSubTaskUnderTaskCase", new CreateSubTaskUnderTaskCaseTask(jiraServiceSubTaskCreator));
        Mocks.register("RelatesSubtaskToCloneIssue", new RelatesSubtaskToCloneIssueTask(jiraServiceClone, issueLinkConverter));
    }

    @Test
    public void taskCasesAreAbsent() {
        ProcessScenario main = mock(ProcessScenario.class);
        when(jiraServiceCheck.checkIfTestCasesExist()).thenReturn(false);
        when(main.waitsAtUserTask("PrepareIssueKeyInTasklist")).thenReturn(
                TaskDelegate::complete
        );
        Scenario.run(main).startByKey("cloneIssueAndCreateSubTasks").execute();

        verify(main).hasStarted("Event_0aq075u");
        verify(main).hasCompleted("FindIssueByProjectKeyTask");
        verify(main).hasCompleted("CheckTaskCasesInIssueTask");
        verify(main, never()).hasStarted("CollectTaskCasesTask");
        verify(main).hasFinished("TasksAreAbsentEndEvent");
    }

    @Test
    public void doneProcess() {

        ProcessScenario main = mock(ProcessScenario.class);
        when(jiraServiceCheck.checkIfTestCasesExist()).thenReturn(true);

        when(main.waitsAtUserTask("PrepareIssueKeyInTasklist")).thenReturn(
                TaskDelegate::complete
        );
        Scenario.run(main).startByKey("cloneIssueAndCreateSubTasks").execute();
        verify(main).hasStarted("Event_0aq075u");
        verify(main).hasCompleted("PrepareIssueKeyInTasklist");
        verify(main).hasCompleted("FindIssueByProjectKeyTask");
        verify(main).hasCompleted("CheckTaskCasesInIssueTask");
        verify(main).hasCompleted("CollectTaskCasesTask");
        verify(main).hasCompleted("CloneIssueTask");
        verify(main).hasStarted("RelatedLinksToCloneSubProcess#multiInstanceBody");
        verify(main).hasFinished("ProcessEndedEndEvent");
    }

    @Test
    public void doneProcessMessageStartEvent() {
        ProcessScenario main = mock(ProcessScenario.class);
        when(jiraServiceCheck.checkIfTestCasesExist()).thenReturn(true);
        Scenario.run(main).startByMessage("IssueKey").execute();
        verify(main).hasStarted("ProcessStartedStartMessageEvent");
        verify(main).hasCompleted("FindIssueByProjectKeyTask");
        verify(main).hasCompleted("CheckTaskCasesInIssueTask");
        verify(main).hasCompleted("CollectTaskCasesTask");
        verify(main).hasCompleted("CloneIssueTask");
        verify(main).hasStarted("RelatedLinksToCloneSubProcess#multiInstanceBody");
        verify(main).hasFinished("ProcessEndedEndEvent");

    }

}
