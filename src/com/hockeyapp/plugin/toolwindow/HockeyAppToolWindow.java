package com.hockeyapp.plugin.toolwindow;

import com.hockeyapp.plugin.actions.LoadCrashGroupsAction;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.components.JBList;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by tsaravana on 6/21/2015.
 */
public class HockeyAppToolWindow implements ToolWindowFactory {

    private JComponent parentPanel;
    private JPanel contentPanel;
    private JPanel leftPanel;
    private JPanel rightPanel;
    private JBList listCrashGroups;

    @Override
    public void createToolWindowContent(Project project, ToolWindow toolWindow) {
        toolWindow.setTitle("HockeyApp");
        SimpleToolWindowPanel panel = new SimpleToolWindowPanel(false, true);
        final Content content = ContentFactory.SERVICE.getInstance().createContent(panel, "COOL", false);
        toolWindow.getContentManager().addContent(content);

        panel.setContent(contentPanel);

        DefaultActionGroup group = new DefaultActionGroup();

        Map<String,String> params = new HashMap<String, String>();
        params.put("sort","number_of_crashes");
        params.put("order","desc");
        final LoadCrashGroupsAction action = new LoadCrashGroupsAction("8ad01ef994b37f234069d4d018fc1f5a", params, listCrashGroups);

        group.add(action);
        ActionToolbar toolbar = ActionManager.getInstance().createActionToolbar(ActionPlaces.UNKNOWN, group, false);

        toolbar.setTargetComponent(panel);

        panel.setToolbar(toolbar.getComponent());
    }



}
