
package com.hockeyapp.core.network.models.crash;

import com.google.gson.annotations.Expose;

public class CrashInfo {

    @Expose
    private Crash crash;
    @Expose
    private String status;

    /**
     * @return The crash
     */
    public Crash getCrash() {
        return crash;
    }

    /**
     * @param crash The crash
     */
    public void setCrash(Crash crash) {
        this.crash = crash;
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
