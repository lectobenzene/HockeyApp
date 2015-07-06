package com.hockeyapp.plugin.actions;

import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.HockeyAppView;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

/**
 * Created by tsaravana on 6/30/2015.
 */
public class LoadCrashGroupsAction extends AnAction {
    public static final String ACTION_ID = "HockeyApp.LoadCrashGroupsAction";

    private Map<String, String> params;
    private Project project;
    private String appId;

    public LoadCrashGroupsAction() {
        super(AllIcons.Actions.ForceRefresh);
    }

    public LoadCrashGroupsAction(Map<String, String> params) {
        this();
        this.params = params;
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        project = e.getProject();
        if (appId == null && project != null) {
            appId = HAPreferenceManager.getInstance().getAppId(project);
        }
        final Presentation presentation = e.getPresentation();
        if (appId != null) {
            presentation.setEnabled(true);
        } else {
            presentation.setEnabled(false);
        }
    }

    public void actionPerformed(@NotNull AnActionEvent e) {

        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Get Crash Groups") {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                indicator.setText("Obtaining Crash Groups");
                HockeyAppView.getInstance().fillCrashGroups(true);
            }
        });

    }

}
