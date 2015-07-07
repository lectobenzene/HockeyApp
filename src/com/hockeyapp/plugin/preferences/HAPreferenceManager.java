package com.hockeyapp.plugin.preferences;

import com.hockeyapp.plugin.preferences.models.DataStore;
import com.hockeyapp.plugin.preferences.models.PreferenceStore;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by tsaravana on 7/5/2015.
 */
public class HAPreferenceManager {
    private static HAPreferenceManager instance;


    private HAPreferenceManager() {
    }

    @NotNull
    public static HAPreferenceManager getInstance() {
        if (instance == null) {
            synchronized (HAPreferenceManager.class) {
                if (instance == null) {
                    instance = new HAPreferenceManager();
                }
            }
        }
        return instance;
    }

    @Nullable
    public String getApiToken() {
        final PreferenceStore store = getPreferenceStore();
        return store.getApiToken();
    }

    public void setApiToken(String apiToken) {
        final PreferenceStore store = getPreferenceStore();
        store.setApiToken(apiToken);
        setPreferenceStore(store);
    }

    @Nullable
    public String getAppId(Project project) {
        final DataStore dataStore = getDataStore(project);
        return dataStore.getAppId();
    }

    public void setAppId(Project project, String appId) {
        final DataStore dataStore = getDataStore(project);
        dataStore.setAppId(appId);
        setDataStore(project, dataStore);
    }

    @NotNull
    private DataStore getDataStore(Project project) {
        final HAPreferenceService service = ServiceManager.getService(project, HAPreferenceService.class);
        DataStore store = service.getState();
        if (store == null) {
            store = new DataStore();
        }
        return store;
    }

    private void setDataStore(Project project, @NotNull DataStore store) {
        final HAPreferenceService service = ServiceManager.getService(project, HAPreferenceService.class);
        service.loadState(store);
    }

    @NotNull
    private PreferenceStore getPreferenceStore() {
        final PreferenceService service = ServiceManager.getService(PreferenceService.class);
        PreferenceStore store = service.getState();
        if (store == null) {
            store = new PreferenceStore();
        }
        return store;
    }

    private void setPreferenceStore(@NotNull PreferenceStore store) {
        final PreferenceService service = ServiceManager.getService(PreferenceService.class);
        service.loadState(store);
    }


}
