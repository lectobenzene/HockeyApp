package com.hockeyapp.core.network.models.listcrashes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hockeyapp.core.network.models.crashreasons.CrashReason;

import java.util.ArrayList;
import java.util.List;

public class ListCrashes {

    @SerializedName("crash_reason")
    @Expose
    private CrashReason crashReason;
    @Expose
    private List<Crash> crashes = new ArrayList<Crash>();
    @Expose
    private String status;
    @SerializedName("current_page")
    @Expose
    private Long currentPage;
    @SerializedName("per_page")
    @Expose
    private Long perPage;
    @SerializedName("total_entries")
    @Expose
    private Long totalEntries;
    @SerializedName("total_pages")
    @Expose
    private Long totalPages;

    /**
     * @return The crashReason
     */
    public CrashReason getCrashReason() {
        return crashReason;
    }

    /**
     * @param crashReason The crash_reason
     */
    public void setCrashReason(CrashReason crashReason) {
        this.crashReason = crashReason;
    }

    /**
     * @return The crashes
     */
    public List<Crash> getCrashes() {
        return crashes;
    }

    /**
     * @param crashes The crashes
     */
    public void setCrashes(List<Crash> crashes) {
        this.crashes = crashes;
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

    /**
     * @return The currentPage
     */
    public Long getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage The current_page
     */
    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * @return The perPage
     */
    public Long getPerPage() {
        return perPage;
    }

    /**
     * @param perPage The per_page
     */
    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    /**
     * @return The totalEntries
     */
    public Long getTotalEntries() {
        return totalEntries;
    }

    /**
     * @param totalEntries The total_entries
     */
    public void setTotalEntries(Long totalEntries) {
        this.totalEntries = totalEntries;
    }

    /**
     * @return The totalPages
     */
    public Long getTotalPages() {
        return totalPages;
    }

    /**
     * @param totalPages The total_pages
     */
    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

}
