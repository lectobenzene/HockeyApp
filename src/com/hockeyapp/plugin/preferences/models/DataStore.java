package com.hockeyapp.plugin.preferences.models;

/**
 * Created by tsaravana on 7/6/2015.
 */
public class DataStore {
    private String appId;
    private int filterCrashGroups = 1; // FILTER_ALL
    private boolean autoSync = true; // Perform Auto Sync by default

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getFilterCrashGroups() {
        return filterCrashGroups;
    }

    public void setFilterCrashGroups(int filterCrashGroups) {
        this.filterCrashGroups = filterCrashGroups;
    }

    public boolean isAutoSync() {
        return autoSync;
    }

    public void setAutoSync(boolean autoSync) {
        this.autoSync = autoSync;
    }
}
