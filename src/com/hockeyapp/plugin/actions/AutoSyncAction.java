package com.hockeyapp.plugin.actions;

import com.hockeyapp.core.network.AutoSyncManager;
import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;

/**
 * Created by tsaravana on 7/7/2015.
 */
public class AutoSyncAction extends ToggleAction {
    public static final String ACTION_ID = "HockeyApp.AutoSyncAction";
    private Project project;
    private String appId;

    public AutoSyncAction() {
        super("Auto Sync", "Continually syncs with Hockey App and updates the crash groups", AllIcons.Actions.Download);
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
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

    @Override
    public boolean isSelected(AnActionEvent e) {
        boolean autoSync = false;
        if (e.getProject() != null) {
            autoSync = HAPreferenceManager.getInstance().isAutoSync(e.getProject());
        }
        return autoSync;
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
        HAPreferenceManager.getInstance().setAutoSync(e.getProject(), state);
        if (state) {
            AutoSyncManager.getInstance().syncCrashReasons();
        }
    }

}
