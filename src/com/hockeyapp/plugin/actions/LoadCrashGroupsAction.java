package com.hockeyapp.plugin.actions;

import com.hockeyapp.core.network.NetworkService;
import com.hockeyapp.core.network.models.crashreasons.CrashGroups;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.hockeyapp.plugin.preferences.AssociateApplicationService;
import com.hockeyapp.plugin.toolwindow.swingcomponents.CrashGroupsCellRenderer;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.ui.components.JBList;

import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by tsaravana on 6/30/2015.
 */
public class LoadCrashGroupsAction extends AnAction {

    private JBList listCrashGroups = null;
    private String appId;
    private Map<String, String> params;

    public LoadCrashGroupsAction() {
        super(AllIcons.General.Gear);
    }

    public LoadCrashGroupsAction(String appId, Map<String, String> params) {
        this();
        this.appId = appId;
        this.params = params;
    }

    public LoadCrashGroupsAction(String s, Map<String, String> params, JBList listCrashGroups) {
        this(s, params);
        this.listCrashGroups = listCrashGroups;
    }

    public void actionPerformed(AnActionEvent e) {
        if (appId == null) {
            final AssociateApplicationService service = ServiceManager.getService(e.getProject(), AssociateApplicationService.class);
            appId = service.getState();
        }

        final CrashGroups crashGroups = NetworkService.getCrashReasons(appId, params);
        if (crashGroups != null) {
            final List<CrashReason> crashReasons = crashGroups.getCrashReasons();
            for (CrashReason crashReason : crashReasons) {
                System.out.println("crashReason.getGroupingHash() = " + crashReason.getGroupingHash());
            }
            if (listCrashGroups != null) {
                listCrashGroups.setCellRenderer(new CrashGroupsCellRenderer());
                listCrashGroups.setListData(new Vector<CrashReason>(crashReasons));
            }
        }

    }
}
