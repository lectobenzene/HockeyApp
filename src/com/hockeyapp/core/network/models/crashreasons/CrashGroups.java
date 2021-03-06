
package com.hockeyapp.core.network.models.crashreasons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class CrashGroups {

    @SerializedName("crash_reasons")
    @Expose
    private List<CrashReason> crashReasons = new ArrayList<CrashReason>();
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
     * 
     * @return
     *     The crashReasons
     */
    public List<CrashReason> getCrashReasons() {
        return crashReasons;
    }

    /**
     * 
     * @param crashReasons
     *     The crash_reasons
     */
    public void setCrashReasons(List<CrashReason> crashReasons) {
        this.crashReasons = crashReasons;
    }

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The currentPage
     */
    public Long getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The current_page
     */
    public void setCurrentPage(Long currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The perPage
     */
    public Long getPerPage() {
        return perPage;
    }

    /**
     * 
     * @param perPage
     *     The per_page
     */
    public void setPerPage(Long perPage) {
        this.perPage = perPage;
    }

    /**
     * 
     * @return
     *     The totalEntries
     */
    public Long getTotalEntries() {
        return totalEntries;
    }

    /**
     * 
     * @param totalEntries
     *     The total_entries
     */
    public void setTotalEntries(Long totalEntries) {
        this.totalEntries = totalEntries;
    }

    /**
     * 
     * @return
     *     The totalPages
     */
    public Long getTotalPages() {
        return totalPages;
    }

    /**
     * 
     * @param totalPages
     *     The total_pages
     */
    public void setTotalPages(Long totalPages) {
        this.totalPages = totalPages;
    }

}
