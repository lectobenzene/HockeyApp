package com.hockeyapp.plugin.preferences.models;

/**
 * Created by tsaravana on 7/6/2015.
 */
public class DataStore {
    private String appId;
    private boolean filterOpen = true;
    private boolean filterResolved = true;
    private boolean filterIgnored = true;
    private boolean autoSync = true; // Perform Auto Sync by default

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public boolean isAutoSync() {
        return autoSync;
    }

    public void setAutoSync(boolean autoSync) {
        this.autoSync = autoSync;
    }

    public boolean isFilterOpen() {
        return filterOpen;
    }

    public void setFilterOpen(boolean filterOpen) {
        this.filterOpen = filterOpen;
    }

    public boolean isFilterResolved() {
        return filterResolved;
    }

    public void setFilterResolved(boolean filterResolved) {
        this.filterResolved = filterResolved;
    }

    public boolean isFilterIgnored() {
        return filterIgnored;
    }

    public void setFilterIgnored(boolean filterIgnored) {
        this.filterIgnored = filterIgnored;
    }
}
