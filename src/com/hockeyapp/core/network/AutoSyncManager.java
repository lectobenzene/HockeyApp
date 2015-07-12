package com.hockeyapp.core.network;

import com.hockeyapp.core.network.models.crashreasons.CrashGroups;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;
import com.hockeyapp.plugin.preferences.HAPreferenceManager;
import com.hockeyapp.plugin.toolwindow.HockeyAppView;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by tsaravana on 7/8/2015.
 */
public class AutoSyncManager {

    private static AutoSyncManager instance;
    private final List<CrashReason> crashReasons = new ArrayList<CrashReason>();
    private final List<CrashReason> filteredCrashReasons = new ArrayList<CrashReason>();
    private Long currentPage = 1L;
    private Long totalPages = 1L;
    private List<AutoSyncListener> listeners = new ArrayList<AutoSyncListener>();

    private AutoSyncManager() {

    }


    public static AutoSyncManager getInstance() {
        if (instance == null) {
            synchronized (AutoSyncManager.class) {
                if (instance == null) {
                    instance = new AutoSyncManager();
                }
            }
        }
        return instance;
    }

    public void forceSyncCrashReasons() {
        crashReasons.clear();
        currentPage = 1L;
        final Project project = HockeyAppView.getInstance().getProject();
        final String appId = HAPreferenceManager.getInstance().getAppId(project);
        Map<String, String> params = new HashMap<String, String>();
        params.put("sort", "number_of_crashes");
        params.put("order", "desc");
        params.put("page", currentPage.toString());

        CrashGroups crashGroups = NetworkService.getCrashReasons(appId, params);
        final List<CrashReason> crashReasonsBunch = crashGroups.getCrashReasons();
        crashReasons.addAll(crashReasonsBunch);
        listenerUpdate(crashReasonsBunch);
        currentPage = crashGroups.getCurrentPage();
        totalPages = crashGroups.getTotalPages();
    }

    public void syncCrashReasons() {
        //crashReasons.clear();
        final Project project = HockeyAppView.getInstance().getProject();
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Syncing Crash groups", true) {
            @Override
            public void run(@NotNull ProgressIndicator indicator) {
                final String appId = HAPreferenceManager.getInstance().getAppId(project);
                Map<String, String> params = new HashMap<String, String>();
                params.put("sort", "number_of_crashes");
                params.put("order", "desc");
                currentPage++;
                while (totalPages >= currentPage) {
                    params.put("page", currentPage.toString());
                    indicator.setText(String.format("Syncing page %d of %d", currentPage, totalPages));
                    indicator.setFraction(((double) currentPage - 1) / totalPages);
                    CrashGroups crashGroups = NetworkService.getCrashReasons(appId, params);
                    final List<CrashReason> crashReasonsBunch = crashGroups.getCrashReasons();
                    crashReasons.addAll(crashReasonsBunch);
                    listenerUpdate(crashReasonsBunch);
                    currentPage = crashGroups.getCurrentPage();
                    totalPages = crashGroups.getTotalPages();
                    currentPage++;
                }
                currentPage--;
            }
        });
    }

    private void listenerUpdate(List<CrashReason> crashReasonsBunch) {
        for (AutoSyncListener listener : listeners) {
            listener.update(crashReasonsBunch);
        }
    }

    public List<CrashReason> getCrashReasons() {
        return crashReasons;
    }

    public void addListener(AutoSyncListener listener) {
        this.listeners.add(listener);
    }

    public Long getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    public List<CrashReason> getFilteredCrashReasons() {
        return filteredCrashReasons;
    }

    public interface AutoSyncListener {
        public void update(List<CrashReason> crashReasonsBunch);
    }
}
