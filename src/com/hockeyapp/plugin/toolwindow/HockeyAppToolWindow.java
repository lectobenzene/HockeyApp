package com.hockeyapp.plugin.toolwindow;

import com.hockeyapp.core.network.AutoSyncManager;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.hockeyapp.plugin.actions.*;
import com.intellij.execution.filters.TextConsoleBuilderFactory;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.JBSplitter;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.List;

/**
 * Created by tsaravana on 6/21/2015.
 */
public class HockeyAppToolWindow implements ToolWindowFactory {

    private JComponent parentPanel;
    private JPanel contentPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JBList listCrashGroups;
    private JBSplitter splitter;
    private JPanel stacktracePanel;
    private JPanel rightToolbarPanel;
    private JPanel leftToolbarPanel;
    private ConsoleView console;
    private Project project;

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        this.project = project;
        toolWindow.setTitle("HockeyApp");

        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(false, true);
        final Content content = ContentFactory.SERVICE.getInstance().createContent(panel, "", false);
        toolWindow.getContentManager().addContent(content);
        panel.setContent(contentPanel);


        initUI();

        // All Action
        final AnAction loadCrashGroupsAction = ActionManager.getInstance().getAction(LoadCrashGroupsAction.ACTION_ID);
        final AnAction associateWithHockeyAppAction = ActionManager.getInstance().getAction(AssociateWithHockeyAppAction.ACTION_ID);
        final AnAction autoSyncAction = ActionManager.getInstance().getAction(AutoSyncAction.ACTION_ID);
        final SortCrashGroupsAction sortCountAction = new SortCrashGroupsAction("Sort by Count", "Sorts the Crash groups by number of crashes", AllIcons.ObjectBrowser.SortedByUsage, SortCrashGroupsAction.SORT_COUNT);
        final SortCrashGroupsAction sortDescriptionAction = new SortCrashGroupsAction("Sort by Description", "Sorts the Crash groups by Description", AllIcons.ObjectBrowser.Sorted, SortCrashGroupsAction.SORT_DESCRIPTION);
        final SortCrashGroupsAction sortDateAction = new SortCrashGroupsAction("Sort by Date", "Sorts the Crash groups by last crash date", IconLoader.getIcon("/icons/sortbyDuration.png"), SortCrashGroupsAction.SORT_LAST_CRASH);
        ActionManager.getInstance().registerAction(SortCrashGroupsAction.ACTION_SORT_COUNT_ID, sortCountAction);
        ActionManager.getInstance().registerAction(SortCrashGroupsAction.ACTION_SORT_DESC_ID, sortDescriptionAction);
        ActionManager.getInstance().registerAction(SortCrashGroupsAction.ACTION_SORT_DATE_ID, sortDateAction);
        final FilterCrashGroupsAction filterAllAction = new FilterCrashGroupsAction("Show All", "Show all the crash groups", IconLoader.getIcon("/icons/filterAll.png"), FilterCrashGroupsAction.FILTER_ALL);
        final FilterCrashGroupsAction filterOpenAction = new FilterCrashGroupsAction("Show UnResolved", "Show unresolved crash groups", IconLoader.getIcon("/icons/filterOpen.png"), FilterCrashGroupsAction.FILTER_OPEN);
        final FilterCrashGroupsAction filterResolvedAction = new FilterCrashGroupsAction("Show Resolved", "Show resolved crash groups", IconLoader.getIcon("/icons/filterResolved.png"), FilterCrashGroupsAction.FILTER_RESOLVED);
        final FilterCrashGroupsAction filterIgnoredAction = new FilterCrashGroupsAction("Show Ignored", "Show ignored crash groups", IconLoader.getIcon("/icons/filterIgnored.png"), FilterCrashGroupsAction.FILTER_IGNORED);
        ActionManager.getInstance().registerAction(FilterCrashGroupsAction.ACTION_FILTER_ALL_ID, filterAllAction);
        ActionManager.getInstance().registerAction(FilterCrashGroupsAction.ACTION_FILTER_OPEN_ID, filterOpenAction);
        ActionManager.getInstance().registerAction(FilterCrashGroupsAction.ACTION_FILTER_RESOLVED_ID, filterResolvedAction);
        ActionManager.getInstance().registerAction(FilterCrashGroupsAction.ACTION_FILTER_IGNORED_ID, filterIgnoredAction);
        final AnAction copyDescriptionAction = ActionManager.getInstance().getAction(CopyDescriptionAction.ACTION_ID);


        // All Groups
        DefaultActionGroup mainGroup = new DefaultActionGroup();
        mainGroup.addAll(loadCrashGroupsAction);
        mainGroup.addSeparator();
        mainGroup.add(associateWithHockeyAppAction);

        DefaultActionGroup leftGroup = new DefaultActionGroup(filterOpenAction, filterResolvedAction, filterIgnoredAction);
        leftGroup.addSeparator();
        leftGroup.addAll(sortCountAction, sortDescriptionAction, sortDateAction);

        DefaultActionGroup rightGroup = new DefaultActionGroup(copyDescriptionAction);

        ActionToolbar mainToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, mainGroup, false);
        mainToolbar.setTargetComponent(panel);
        panel.setToolbar(mainToolbar.getComponent());

        ActionToolbar leftToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, leftGroup, true);
        leftToolbar.setTargetComponent(leftToolbarPanel);
        leftToolbarPanel.add(leftToolbar.getComponent());

        ActionToolbar rightToolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, rightGroup, true);
        rightToolbar.setTargetComponent(rightToolbarPanel);
        rightToolbarPanel.add(rightToolbar.getComponent());

        setListeners();
        HockeyAppView.getInstance().fillCrashGroups(AutoSyncManager.getInstance().getCrashReasons());
    }

    private void setListeners() {
        AutoSyncManager.getInstance().addListener(new AutoSyncManager.AutoSyncListener() {
            @Override
            public void update(List<CrashReason> crashReasonsBunch) {
                HockeyAppView.getInstance().fillCrashGroups(crashReasonsBunch);
            }
        });
    }

    private void initUI() {
        splitter.setSplitterProportionKey("HockeyApp.ToolWindow.JBSplitter.ProportionKey");
        console = TextConsoleBuilderFactory.getInstance().createBuilder(project).getConsole();
        stacktracePanel.add(console.getComponent());
        // Initialize Project field
        final HockeyAppView instance = HockeyAppView.getInstance();
        instance.setProject(project);
        instance.setListCrashGroups(listCrashGroups);
        instance.setConsole(console);
        instance.initUI();
    }

}
