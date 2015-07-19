package com.hockeyapp.plugin.actions;

import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.HockeyAppView;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.Presentation;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

/**
 * Created by tsaravana on 7/19/2015.
 */
public class CopyDescriptionAction extends AnAction {

    public static final String ACTION_ID = "CopyDescriptionAction";
    private String appId;

    public CopyDescriptionAction() {
        super("Copy Description", "Copies description of the crash to clipboard", AllIcons.Actions.Copy);
    }

    public void actionPerformed(AnActionEvent e) {
        final String log = HockeyAppView.getInstance().getLogDescription();
        StringSelection stringSelection = new StringSelection(log);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    @Override
    public void update(AnActionEvent e) {
        super.update(e);
        if (e.getProject() != null) {
            if (appId == null) {
                appId = HAPreferenceManager.getInstance().getAppId(e.getProject());
            }
            final Presentation presentation = e.getPresentation();
            if (appId != null && HockeyAppView.getInstance().getLogDescription() != null) {
                presentation.setEnabled(true);
            } else {
                presentation.setEnabled(false);
            }
        }
    }
}
