package com.hockeyapp.plugin.actions;

import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.actionSystem.ToggleAction;
import com.intellij.openapi.project.Project;

import javax.swing.*;

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
    public static final int FILTER_IGNORED = 3;

    private final int type;
    private Project project;
    private String appId;

    public FilterCrashGroupsAction(String text, String description, Icon icon, int type) {
        super(text, description, icon);
        this.type = type;
    }

    @Override
    public boolean isSelected(AnActionEvent e) {
        System.out.println("FilterCrashGroupsAction isSelected");
        return false;
    }

    @Override
    public void setSelected(AnActionEvent e, boolean state) {
        System.out.println("FilterCrashGroupsAction state = " + state);
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
