package com.hockeyapp.plugin.dialogs;

import com.hockeyapp.core.network.NetworkService;
import com.hockeyapp.core.network.models.listapps.App;
import com.hockeyapp.core.network.models.listapps.ListApps;
import com.hockeyapp.plugin.preferences.AssociateApplicationService;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.util.List;

/**
 * Created by tsaravana on 6/19/2015.
 */
public class ListAppsDialog extends DialogWrapper {

    private JPanel contentPanel;
    private JBTable table;
    private JLabel labelTitle;
    private JLabel labelPlatform;
    private JLabel labelCreated;
    private JLabel labelUpdated;
    private JLabel labelOwner;

    private ListApps apps;
    private App app;

    public ListAppsDialog(Project project) {
        super(project);
        initializeTable(project);
        setTitle("Choose the Application to associate");
        init();
    }

    private void initializeTable(Project project) {
        String[] columnNames = {"Title", "Platform"};
        final DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        apps = NetworkService.getListApps();
        if (apps != null) {
            for (App app : apps.getApps()) {
                tableModel.addRow(new String[]{app.getTitle(), app.getPlatform()});
            }
        }
        table.setModel(tableModel);

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (apps != null) {
                    setInfo(table.getSelectedRow());
                }
            }
        });

        // Default selection to first row
        table.setRowSelectionInterval(0,0);
        // Setting default selection if the project is already associated with a hockeyapp application
        final AssociateApplicationService service = ServiceManager.getService(project, AssociateApplicationService.class);
        final String publicIdentifier = service.getState();
        if (publicIdentifier != null && apps != null) {
            final List<App> listOfApps = apps.getApps();
            for (int index = 0; index < listOfApps.size(); index++) {
                if (listOfApps.get(index).getPublicIdentifier().equals(publicIdentifier)) {
                    table.setRowSelectionInterval(index, index);
                    break;
                }
            }
        }
    }

    private void setInfo(int index) {
        app = apps.getApps().get(index);
        labelTitle.setText(app.getTitle());
        labelPlatform.setText(app.getPlatform());
        labelCreated.setText(app.getCreatedAt());
        labelUpdated.setText(app.getUpdatedAt());
        labelOwner.setText(app.getOwner());
    }

    @Nullable
    @Override
    protected JComponent createCenterPanel() {
        return contentPanel;
    }

    public App getApp() {
        return app;
    }
}
