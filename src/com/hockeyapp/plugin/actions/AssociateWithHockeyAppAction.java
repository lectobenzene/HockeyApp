package com.hockeyapp.plugin.actions;

import com.hockeyapp.core.network.models.listapps.App;
import com.hockeyapp.plugin.dialogs.ListAppsDialog;
import com.hockeyapp.plugin.preferences.AssociateApplicationService;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.util.IconLoader;

/**
 * Created by tsaravana on 6/19/2015.
 */
public class AssociateWithHockeyAppAction extends AnAction {

    private App app = null;

    public AssociateWithHockeyAppAction(){
        super(IconLoader.getIcon("/icons/hockeyappAction.png"));
    }

    public void actionPerformed(AnActionEvent e) {
        ListAppsDialog listAppsDialog = new ListAppsDialog(e.getProject());
        listAppsDialog.show();

        if (listAppsDialog.isOK()) {
            app = listAppsDialog.getApp();
        }

        if (app != null) {
            System.out.println("app.getId() = " + app.getPublicIdentifier());
            final AssociateApplicationService service = ServiceManager.getService(e.getProject(), AssociateApplicationService.class);
            service.loadState(app.getPublicIdentifier());
        }

    }
}
