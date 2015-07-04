
package com.hockeyapp.core.network.models.crashreasons;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CrashReason {

    @Expose
    private Long id;
    @SerializedName("app_id")
    @Expose
    private Long appId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @Expose
    private Long status;
    @Expose
    private String reason;
    @SerializedName("last_crash_at")
    @Expose
    private String lastCrashAt;
    @SerializedName("exception_type")
    @Expose
    private Object exceptionType;
    @Expose
    private Boolean fixed;
    @SerializedName("app_version_id")
    @Expose
    private Long appVersionId;
    @SerializedName("bundle_version")
    @Expose
    private String bundleVersion;
    @SerializedName("bundle_short_version")
    @Expose
    private String bundleShortVersion;
    @SerializedName("number_of_crashes")
    @Expose
    private Long numberOfCrashes;
    @SerializedName("grouping_hash")
    @Expose
    private String groupingHash;
    @SerializedName("grouping_type")
    @Expose
    private Long groupingType;
    @Expose
    private String method;
    @Expose
    private String file;
    @SerializedName("class")
    @Expose
    private String _class;
    @Expose
    private String line;

    /**
     * 
     * @return
     *     The id
     */
    public Long getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The appId
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * 
     * @param appId
     *     The app_id
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * 
     * @return
     *     The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 
     * @param createdAt
     *     The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 
     * @return
     *     The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 
     * @param updatedAt
     *     The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 
     * @return
     *     The status
     */
    public Long getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The reason
     */
    public String getReason() {
        return reason;
    }

    /**
     * 
     * @param reason
     *     The reason
     */
    public void setReason(String reason) {
        this.reason = reason;
    }

    /**
     * 
     * @return
     *     The lastCrashAt
     */
    public String getLastCrashAt() {
        return lastCrashAt;
    }

    /**
     * 
     * @param lastCrashAt
     *     The last_crash_at
     */
    public void setLastCrashAt(String lastCrashAt) {
        this.lastCrashAt = lastCrashAt;
    }

    /**
     * 
     * @return
     *     The exceptionType
     */
    public Object getExceptionType() {
        return exceptionType;
    }

    /**
     * 
     * @param exceptionType
     *     The exception_type
     */
    public void setExceptionType(Object exceptionType) {
        this.exceptionType = exceptionType;
    }

    /**
     * 
     * @return
     *     The fixed
     */
    public Boolean getFixed() {
        return fixed;
    }

    /**
     * 
     * @param fixed
     *     The fixed
     */
    public void setFixed(Boolean fixed) {
        this.fixed = fixed;
    }

    /**
     * 
     * @return
     *     The appVersionId
     */
    public Long getAppVersionId() {
        return appVersionId;
    }

    /**
     * 
     * @param appVersionId
     *     The app_version_id
     */
    public void setAppVersionId(Long appVersionId) {
        this.appVersionId = appVersionId;
    }

    /**
     * 
     * @return
     *     The bundleVersion
     */
    public String getBundleVersion() {
        return bundleVersion;
    }

    /**
     * 
     * @param bundleVersion
     *     The bundle_version
     */
    public void setBundleVersion(String bundleVersion) {
        this.bundleVersion = bundleVersion;
    }

    /**
     * 
     * @return
     *     The bundleShortVersion
     */
    public String getBundleShortVersion() {
        return bundleShortVersion;
    }

    /**
     * 
     * @param bundleShortVersion
     *     The bundle_short_version
     */
    public void setBundleShortVersion(String bundleShortVersion) {
        this.bundleShortVersion = bundleShortVersion;
    }

    /**
     * 
     * @return
     *     The numberOfCrashes
     */
    public Long getNumberOfCrashes() {
        return numberOfCrashes;
    }

    /**
     * 
     * @param numberOfCrashes
     *     The number_of_crashes
     */
    public void setNumberOfCrashes(Long numberOfCrashes) {
        this.numberOfCrashes = numberOfCrashes;
    }

    /**
     * 
     * @return
     *     The groupingHash
     */
    public String getGroupingHash() {
        return groupingHash;
    }

    /**
     * 
     * @param groupingHash
     *     The grouping_hash
     */
    public void setGroupingHash(String groupingHash) {
        this.groupingHash = groupingHash;
    }

    /**
     * 
     * @return
     *     The groupingType
     */
    public Long getGroupingType() {
        return groupingType;
    }

    /**
     * 
     * @param groupingType
     *     The grouping_type
     */
    public void setGroupingType(Long groupingType) {
        this.groupingType = groupingType;
    }

    /**
     * 
     * @return
     *     The method
     */
    public String getMethod() {
        return method;
    }

    /**
     * 
     * @param method
     *     The method
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     * 
     * @return
     *     The file
     */
    public String getFile() {
        return file;
    }

    /**
     * 
     * @param file
     *     The file
     */
    public void setFile(String file) {
        this.file = file;
    }

    /**
     * 
     * @return
     *     The _class
     */
    public String getClass_() {
        return _class;
    }

    /**
     * 
     * @param _class
     *     The class
     */
    public void setClass_(String _class) {
        this._class = _class;
    }

    /**
     * 
     * @return
     *     The line
     */
    public String getLine() {
        return line;
    }

    /**
     * 
     * @param line
     *     The line
     */
    public void setLine(String line) {
        this.line = line;
    }

}
