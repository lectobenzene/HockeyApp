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

    private boolean isSelected;

    public FilterCrashGroupsAction(String text, String description, Icon icon, int type) {
        super(text, description, icon);
        this.type = type;
    }

    @Override
    public boolean isSelected(AnActionEvent e) {
        System.out.println("FilterCrashGroupsAction isSelected = " + isSelected);
        return isSelected;
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
        final List<CrashReason> crashReasons = AutoSyncManager.getInstance().getCrashReasons();
        if (crashReasons != null) {
            List<CrashReason> filteredList = AutoSyncManager.getInstance().getFilteredCrashReasons();
            switch (type) {
                case FILTER_OPEN:
                    filter(crashReasons, 0, state);
                    break;
                case FILTER_IGNORED:
                    filter(crashReasons, 2, state);
                    break;
                case FILTER_RESOLVED:
                    filter(crashReasons, 1, state);
                    break;
            }
            HockeyAppView.getInstance().intimate(filteredList);
        }
        System.out.println("FilterCrashGroupsAction state = " + state);
        isSelected = state;
    }

    private List<CrashReason> filter(List<CrashReason> crashReasons, int status, boolean isAdd) {
        List<CrashReason> list = AutoSyncManager.getInstance().getFilteredCrashReasons();
        for (CrashReason crashReason : crashReasons) {
            if (crashReason.getStatus() == status) {
                if (isAdd) {
                    list.add(crashReason);
                } else {
                    list.remove(crashReason);
                }
            }
        }
        return list;
    }

    @Override
    public void update(AnActionEvent e) {
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
