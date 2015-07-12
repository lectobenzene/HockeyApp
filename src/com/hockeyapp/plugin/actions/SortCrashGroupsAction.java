package com.hockeyapp.plugin.actions;

import com.hockeyapp.core.network.AutoSyncManager;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.HockeyAppView;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

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
    private static final int ORDER_DESC = 1;
    private static final int ORDER_ASC = -1;
    private final int type;
    private Project project;
    private String appId;
    private int order;

    public SortCrashGroupsAction(String text, String description, Icon icon, int type) {
        super(text, description, icon);
        this.type = type;
    }

    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final List<CrashReason> crashReasons = AutoSyncManager.getInstance().getFilteredCrashReasons();
        if (crashReasons != null) {
            switch (type) {
                case SORT_COUNT:
                    Collections.sort(crashReasons, getCountComparator(getOrder()));
                    break;
                case SORT_DESCRIPTION:
                    Collections.sort(crashReasons, getDescriptionComparator(getOrder()));
                    break;
                case SORT_LAST_CRASH:
                    Collections.sort(crashReasons, getLastCrashComparator(getOrder()));
                    break;
                default:
                    System.out.println("Something wrong here");
            }
            HockeyAppView.getInstance().intimate(crashReasons);
        }
    }

    private Comparator<CrashReason> getCountComparator(final int order) {
        return new Comparator<CrashReason>() {
            @Override
            public int compare(CrashReason o1, CrashReason o2) {
                if (o1.getNumberOfCrashes() > o2.getNumberOfCrashes()) {
                    return order;
                } else {
                    return -order;
                }
            }
        };
    }

    private Comparator<CrashReason> getDescriptionComparator(final int order) {
        return new Comparator<CrashReason>() {
            @Override
            public int compare(CrashReason o1, CrashReason o2) {
                String class1 = o1.getClass_();
                String class2 = o2.getClass_();
                class1 = class1 == null ? "" : class1;
                class2 = class2 == null ? "" : class2;
                return class1.compareTo(class2) * order;
            }
        };
    }

    private Comparator<CrashReason> getLastCrashComparator(final int order) {
        final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd\'T\'HH:mm:ss");
        return new Comparator<CrashReason>() {
            @Override
            public int compare(CrashReason o1, CrashReason o2) {

                final String crash1 = o1.getLastCrashAt();
                final String crash2 = o2.getLastCrashAt();
                if (crash1 != null && crash2 != null) {
                    try {
                        final Date date1 = format.parse(crash1);
                        final Date date2 = format.parse(crash2);
                        return date1.compareTo(date2) * order;
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                return 0;
            }
        };
    }

    private int getOrder() {
        if (ORDER_DESC == order) {
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
