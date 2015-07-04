package com.hockeyapp.plugin.actions;

import com.hockeyapp.core.network.NetworkService;
import com.hockeyapp.core.network.models.crashreasons.CrashGroups;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.hockeyapp.core.network.models.listcrashes.Crash;
import com.hockeyapp.core.network.models.listcrashes.ListCrashes;
import com.hockeyapp.plugin.preferences.AssociateApplicationService;
import com.hockeyapp.plugin.toolwindow.swingcomponents.CrashGroupsCellRenderer;
import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.OpenFileDescriptor;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiJavaFile;
import com.intellij.psi.search.PsiShortNamesCache;
import com.intellij.ui.components.JBList;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.List;
import java.util.Map;
import java.util.Vector;

/**
 * Created by tsaravana on 6/30/2015.
 */
public class LoadCrashGroupsAction extends AnAction {

    private JTextPane tpStackTrace = null;
    private JBList listCrashGroups = null;
    private String appId;
    private Map<String, String> params;
    private Project project;

    public LoadCrashGroupsAction() {
        super(AllIcons.General.Gear);
    }

    public LoadCrashGroupsAction(String appId, Map<String, String> params) {
        this();
        this.appId = appId;
        this.params = params;
    }

    public LoadCrashGroupsAction(String s, Map<String, String> params, JBList listCrashGroups, JTextPane tpStackTrace) {
        this(s, params);
        this.tpStackTrace = tpStackTrace;
        this.listCrashGroups = listCrashGroups;
    }

    public void actionPerformed(@NotNull AnActionEvent e) {
        project = e.getProject();
        final AssociateApplicationService service = ServiceManager.getService(project, AssociateApplicationService.class);
        appId = service.getState();

        final ListSelectionListener listSelectionListener = new ListSelectionListener() {

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (e.getValueIsAdjusting()) {
                    final CrashReason selCrashReason = (CrashReason) listCrashGroups.getSelectedValue();
                    final Long crashGroupId = selCrashReason.getId();
                    System.out.println("Crash Group ID = " + crashGroupId);
                    openFileInEditor(selCrashReason);

                    ProgressManager.getInstance().run(new Task.Backgroundable(project, "Vool de Run", true) {
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
        final CrashGroups crashGroups = NetworkService.getCrashReasons(appId, params);
        if (crashGroups != null) {
            final List<CrashReason> crashReasons = crashGroups.getCrashReasons();
            for (CrashReason crashReason : crashReasons) {
                System.out.println("crashReason.getGroupingHash() = " + crashReason.getGroupingHash());
            }
            if (listCrashGroups != null) {
                listCrashGroups.setCellRenderer(new CrashGroupsCellRenderer());
                listCrashGroups.setListData(new Vector<CrashReason>(crashReasons));
                listCrashGroups.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
                listCrashGroups.getSelectionModel().addListSelectionListener(listSelectionListener);
            }
        }

    }

    private void openFileInEditor(CrashReason selCrashReason) {
        final String file = selCrashReason.getFile();
        int line = Integer.parseInt(selCrashReason.getLine());
        final PsiFile[] filesByName = PsiShortNamesCache.getInstance(project).getFilesByName(file);
        VirtualFile vFile = null;
        for (PsiFile psiFile : filesByName) {
            System.out.println("psiFile.getName() = " + psiFile.getName());
            System.out.println("psiFile.getPackage " + ((PsiJavaFile) psiFile).getPackageName());
            vFile = psiFile.getVirtualFile();
        }
        if (vFile != null) {
            if (line != 0) {
                line -= 1;
            }
            FileEditorManager.getInstance(project).openEditor(new OpenFileDescriptor(project, vFile, line, 0), true);
        }
    }

    private void populateStackTrace(String crashId) {
        if (crashId != null) {
            final String stackTrace = NetworkService.getStackTrace(appId, crashId);
            System.out.println("stackTrace = " + stackTrace);
            tpStackTrace.setText(stackTrace);
        }
    }

    @Nullable
    private String getCrashId(String crashGroupId) {
        final ListCrashes listCrashes = NetworkService.getListCrashes(appId, crashGroupId, null);
        if (listCrashes != null) {
            final List<Crash> crashes = listCrashes.getCrashes();
            final Crash crash = crashes.get(0);
            final Long crashId = crash.getId();
            System.out.println("crashId = " + crashId);
            return String.valueOf(crashId);
        }
        return null;
    }
}
