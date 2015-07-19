package com.hockeyapp.plugin.actions;

import com.hockeyapp.core.network.AutoSyncManager;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.HockeyAppView;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;

import javax.swing.*;
import java.util.List;

/**
 * Created by tsaravana on 7/6/2015.
 */
public class FilterCrashGroupsAction extends ToggleAction {
    public static final String ACTION_FILTER_ALL_ID = "HockeyApp.FilterAll";
    public static final String ACTION_FILTER_OPEN_ID = "HockeyApp.FilterOpen";
    public static final String ACTION_FILTER_RESOLVED_ID = "HockeyApp.FilterResolved";
    public static final String ACTION_FILTER_IGNORED_ID = "HockeyApp.FilterIgnored";

    public static final int FILTER_ALL = 1;
    public static final int FILTER_OPEN = 2;
    public static final int FILTER_RESOLVED = 3;
    public static final int FILTER_IGNORED = 4;

    private final int type;
    private Project project;
    private String appId;

    public FilterCrashGroupsAction(String text, String description, Icon icon, int type) {
        super(text, description, icon);
        this.type = type;
    }

    @Override
    public boolean isSelected(AnActionEvent e) {
        if (e.getProject() != null) {
            switch (type) {
                case FILTER_OPEN:
                    final boolean filterOpen = HAPreferenceManager.getInstance().isFilterOpen(e.getProject());
                    return filterOpen;
                case FILTER_IGNORED:
                    final boolean filterIgnored = HAPreferenceManager.getInstance().isFilterIgnored(e.getProject());
                    return filterIgnored;
                case FILTER_RESOLVED:
                    final boolean filterResolved = HAPreferenceManager.getInstance().isFilterResolved(e.getProject());
                    return filterResolved;
            }
        }
        return false;
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
        switch (type) {
            case FILTER_OPEN:
                HAPreferenceManager.getInstance().setFilterOpen(e.getProject(), state);
                break;
            case FILTER_IGNORED:
                HAPreferenceManager.getInstance().setFilterIgnored(e.getProject(), state);
                break;
            case FILTER_RESOLVED:
                HAPreferenceManager.getInstance().setFilterResolved(e.getProject(), state);
                break;
        }
        final List<CrashReason> crashReasons = AutoSyncManager.getInstance().getCrashReasons();
        if (crashReasons != null) {
            AutoSyncManager.getInstance().filter();
            HockeyAppView.getInstance().intimate();
        }

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
}
