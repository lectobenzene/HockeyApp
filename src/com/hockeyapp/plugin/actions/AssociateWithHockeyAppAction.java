package com.hockeyapp.plugin.actions;

import com.hockeyapp.core.network.NetworkService;
import com.hockeyapp.core.network.models.listapps.App;
import com.hockeyapp.core.network.models.listapps.ListApps;
import com.hockeyapp.plugin.dialogs.ListAppsDialog;
import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.HockeyAppView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

/**
 * Created by tsaravana on 6/19/2015.
 */
public class AssociateWithHockeyAppAction extends AnAction {
    public static final String ACTION_ID = "HockeyApp.AssociateWithHockeyApp";

    private App app = null;
    private ListApps apps;
    private Project project;

    public void actionPerformed(@NotNull final AnActionEvent e) {
        // Initialize Project field
        HockeyAppView.getInstance().setProject(e.getProject());
        showAssociationDialog(e);
    }

    private void showAssociationDialog(@NotNull final AnActionEvent e) {

        project = e.getProject();
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Associate HockeyApp", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                if (apps == null) {
                    apps = NetworkService.getListApps();
                }
            }

            @Override
            public void onSuccess() {
                if (apps != null) {
                    ListAppsDialog listAppsDialog = new ListAppsDialog(e.getProject(), apps);
                    listAppsDialog.show();

                    if (listAppsDialog.isOK()) {
                        app = listAppsDialog.getApp();
                    }

                    if (app != null) {
                        System.out.println("app.getId() = " + app.getPublicIdentifier());
                        HAPreferenceManager.getInstance().setAppId(project, app.getPublicIdentifier());
                    }
                    fillCrashGroups();
                }
            }
        });
    }

    public void fillCrashGroups() {
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Obtain Crash Groups", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                HockeyAppView.getInstance().fillCrashGroups(true);
            }
        });
    }
}
