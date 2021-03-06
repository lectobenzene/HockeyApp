package com.hockeyapp.plugin.toolwindow;

import com.hockeyapp.core.network.AutoSyncManager;
import com.hockeyapp.core.network.NetworkService;
import com.hockeyapp.core.network.models.crashreasons.CrashGroups;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.hockeyapp.core.network.models.listcrashes.Crash;
import com.hockeyapp.core.network.models.listcrashes.ListCrashes;
import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.swingcomponents.CrashGroupsCellRenderer;
import com.intellij.execution.ui.ConsoleView;
import com.intellij.execution.ui.ConsoleViewContentType;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.ui.CollectionListModel;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.util.Map;

/**
 * Created by tsaravana on 7/5/2015.
 */
public class HockeyAppView {

    private static HockeyAppView instance;

    private Project project;
    private CrashGroups crashGroups;
    private JBList listCrashGroups;
    private ConsoleView console;
    private ListSelectionListener listSelectionListener;
    private CollectionListModel<CrashReason> listModel;
    private String logDescription;

    private HockeyAppView() {
    }

    public static HockeyAppView getInstance() {
        if (instance == null) {
            synchronized (HockeyAppView.class) {
                if (instance == null) {
                    instance = new HockeyAppView();
                }
            }
        }
        return instance;
    }

    public void fillCrashGroups(Map<String, String> params, boolean forceFill) {
        if (forceFill) {
            if (listModel != null) {
                listModel.removeAll();
            }
            AutoSyncManager.getInstance().forceSyncCrashReasons();
        }
    }

    public void fillCrashGroups(boolean forceFill) {
        fillCrashGroups(null, forceFill);
    }

    public void fillCrashGroups(Map<String, String> params) {
        fillCrashGroups(params, true);
    }

    public void fillCrashGroups(List<CrashReason> crashReasonsBunch) {
        listModel.add(crashReasonsBunch);
    }

    public void initUI() {
        listSelectionListener = getSelectionListener();
        listModel = new CollectionListModel<CrashReason>();
        listCrashGroups.setModel(listModel);
        listCrashGroups.setCellRenderer(new CrashGroupsCellRenderer());
        listCrashGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listCrashGroups.getSelectionModel().addListSelectionListener(listSelectionListener);
    }

    private void openFileInEditor(CrashReason selCrashReason) {
        final String file = selCrashReason.getFile();
        int line = 0;
        if (selCrashReason.getLine() != null) {
            line = Integer.parseInt(selCrashReason.getLine());
            final PsiFile[] filesByName = PsiShortNamesCache.getInstance(project).getFilesByName(file);
            VirtualFile vFile = null;
            for (PsiFile psiFile : filesByName) {
                vFile = psiFile.getVirtualFile();
            }
            if (vFile != null) {
                if (line != 0) {
                    line -= 1;
                }
                FileEditorManager.getInstance(project).openEditor(new OpenFileDescriptor(project, vFile, line, 0), true);
            }
        }
    }

    private void populateStackTrace(String crashId) {
        if (crashId != null) {
            HockeyAppView.getInstance().setLogDescription(null);
            final String stackTrace = NetworkService.getStackTrace(HAPreferenceManager.getInstance().getAppId(project), crashId);
            final String logDescription = NetworkService.getLogDescription(HAPreferenceManager.getInstance().getAppId(project), crashId);
            HockeyAppView.getInstance().setLogDescription(logDescription);
            if (stackTrace != null) {
                console.clear();
                console.print(stackTrace, ConsoleViewContentType.ERROR_OUTPUT);
            }
        }
    }

    @Nullable
    private String getCrashId(String crashGroupId) {
        final ListCrashes listCrashes = NetworkService.getListCrashes(HAPreferenceManager.getInstance().getAppId(project), crashGroupId, null);
        if (listCrashes != null) {
            final List<Crash> crashes = listCrashes.getCrashes();
            final Crash crash = crashes.get(0);
            final Long crashId = crash.getId();
            return String.valueOf(crashId);
        }
        return null;
    }

    @NotNull
    private ListSelectionListener getSelectionListener() {
        if (listSelectionListener == null) {
            listSelectionListener = new ListSelectionListener() {

                @Override
                public void valueChanged(ListSelectionEvent e) {
                    if (e.getValueIsAdjusting()) {
                        final CrashReason selCrashReason = (CrashReason) listCrashGroups.getSelectedValue();
                        final Long crashGroupId = selCrashReason.getId();
                        clearOutStackTrace();
                        openFileInEditor(selCrashReason);

                        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Get Crash Info", true) {
                            @Override
                            public void run(@NotNull ProgressIndicator indicator) {
                                indicator.setText("Obtaining the Specific crash info");
                                final String crashId = getCrashId(String.valueOf(crashGroupId));
                                indicator.setFraction(0.3);
                                indicator.setText("Gathering the Stacktrace information");
                                populateStackTrace(crashId);

                                indicator.setFraction(1);
                            }
                        });
                    }
                }
            };
        }
        return listSelectionListener;
    }

    private void clearOutStackTrace() {
        setLogDescription(null);
        getConsole().clear();
    }

    public CrashGroups getCrashGroups() {
        return crashGroups;
    }

    public void setCrashGroups(CrashGroups crashGroups) {
        this.crashGroups = crashGroups;
    }

    public JBList getListCrashGroups() {
        return listCrashGroups;
    }

    public void setListCrashGroups(JBList listCrashGroups) {
        this.listCrashGroups = listCrashGroups;
    }

    public ConsoleView getConsole() {
        return console;
    }

    public void setConsole(ConsoleView console) {
        this.console = console;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public void intimate() {
        listModel.removeAll();
        final List<CrashReason> filteredCrashReasons = AutoSyncManager.getInstance().getFilteredCrashReasons();
        listModel.add(filteredCrashReasons);
    }

    public String getLogDescription() {
        return logDescription;
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = logDescription;
    }
}
