package com.hockeyapp.plugin.actions;

import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.HockeyAppView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tsaravana on 7/5/2015.
 */
public class SortCrashGroupsAction extends AnAction {
    public static final String ACTION_SORT_COUNT_ID = "HockeyApp.SortCount";
    public static final String ACTION_SORT_DESC_ID = "HockeyApp.SortDescription";
    public static final String ACTION_SORT_DATE_ID = "HockeyApp.SortDate";
    public static final int SORT_COUNT = 1;
    public static final int SORT_DESCRIPTION = 2;
    public static final int SORT_LAST_CRASH = 3;
    private static final String ORDER_DESC = "desc";
    private static final String ORDER_ASC = "asc";
    private final int type;
    private Project project;
    private String appId;
    private String order;

    public SortCrashGroupsAction(String text, String description, Icon icon, int type) {
        super(text, description, icon);
        this.type = type;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final Map<String, String> params = new HashMap<String, String>();
        params.put("order", getOrder());
        switch (type) {
            case SORT_COUNT:
                params.put("sort", "number_of_crashes");
                break;
            case SORT_DESCRIPTION:
                params.put("sort", "class");
                break;
            case SORT_LAST_CRASH:
                params.put("sort", "last_crash_at");
                break;
            default:
                System.out.println("Something wrong here");
        }
        ProgressManager.getInstance().run(new Task.Backgroundable(e.getProject(), "Obtain Crash Group", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                HockeyAppView.getInstance().fillCrashGroups(params);
            }
        });
    }

    private String getOrder() {
        if (ORDER_DESC.equals(order)) {
            order = ORDER_ASC;
        } else {
            order = ORDER_DESC;
        }
        return order;
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
}
