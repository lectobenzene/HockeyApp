package com.hockeyapp.core.network.models.listapps;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class ListApps {

    @Expose
    private List<App> apps = new ArrayList<App>();
    @Expose
    private String status;

    /**
     * @return The apps
     */
    public List<App> getApps() {
        return apps;
    }

    /**
     * @param apps The apps
     */
    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    /**
     * @return The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

}
