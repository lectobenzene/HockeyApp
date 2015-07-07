package com.hockeyapp.plugin.preferences.models;

/**
 * Created by tsaravana on 7/6/2015.
 */
public class DataStore {
    private String appId;
    private int filterCrashGroups;

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
}
