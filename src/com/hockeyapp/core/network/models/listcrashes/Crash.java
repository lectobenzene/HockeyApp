package com.hockeyapp.core.network.models.listcrashes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Crash {

    @Expose
    private Long id;
    @SerializedName("app_id")
    @Expose
    private Long appId;
    @SerializedName("crash_reason_id")
    @Expose
    private Long crashReasonId;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @Expose
    private String oem;
    @Expose
    private String model;
    @SerializedName("os_version")
    @Expose
    private String osVersion;
    @SerializedName("jail_break")
    @Expose
    private Object jailBreak;
    @SerializedName("contact_string")
    @Expose
    private String contactString;
    @SerializedName("user_string")
    @Expose
    private String userString;
    @SerializedName("has_log")
    @Expose
    private Boolean hasLog;
    @SerializedName("has_description")
    @Expose
    private Boolean hasDescription;
    @SerializedName("app_version_id")
    @Expose
    private Long appVersionId;
    @SerializedName("bundle_version")
    @Expose
    private String bundleVersion;
    @SerializedName("bundle_short_version")
    @Expose
    private String bundleShortVersion;

    /**
     * @return The id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return The appId
     */
    public Long getAppId() {
        return appId;
    }

    /**
     * @param appId The app_id
     */
    public void setAppId(Long appId) {
        this.appId = appId;
    }

    /**
     * @return The crashReasonId
     */
    public Long getCrashReasonId() {
        return crashReasonId;
    }

    /**
     * @param crashReasonId The crash_reason_id
     */
    public void setCrashReasonId(Long crashReasonId) {
        this.crashReasonId = crashReasonId;
    }

    /**
     * @return The createdAt
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * @param createdAt The created_at
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * @return The updatedAt
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * @param updatedAt The updated_at
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * @return The oem
     */
    public String getOem() {
        return oem;
    }

    /**
     * @param oem The oem
     */
    public void setOem(String oem) {
        this.oem = oem;
    }

    /**
     * @return The model
     */
    public String getModel() {
        return model;
    }

    /**
     * @param model The model
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * @return The osVersion
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * @param osVersion The os_version
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    /**
     * @return The jailBreak
     */
    public Object getJailBreak() {
        return jailBreak;
    }

    /**
     * @param jailBreak The jail_break
     */
    public void setJailBreak(Object jailBreak) {
        this.jailBreak = jailBreak;
    }

    /**
     * @return The contactString
     */
    public String getContactString() {
        return contactString;
    }

    /**
     * @param contactString The contact_string
     */
    public void setContactString(String contactString) {
        this.contactString = contactString;
    }

    /**
     * @return The userString
     */
    public String getUserString() {
        return userString;
    }

    /**
     * @param userString The user_string
     */
    public void setUserString(String userString) {
        this.userString = userString;
    }

    /**
     * @return The hasLog
     */
    public Boolean getHasLog() {
        return hasLog;
    }

    /**
     * @param hasLog The has_log
     */
    public void setHasLog(Boolean hasLog) {
        this.hasLog = hasLog;
    }

    /**
     * @return The hasDescription
     */
    public Boolean getHasDescription() {
        return hasDescription;
    }

    /**
     * @param hasDescription The has_description
     */
    public void setHasDescription(Boolean hasDescription) {
        this.hasDescription = hasDescription;
    }

    /**
     * @return The appVersionId
     */
    public Long getAppVersionId() {
        return appVersionId;
    }

    /**
     * @param appVersionId The app_version_id
     */
    public void setAppVersionId(Long appVersionId) {
        this.appVersionId = appVersionId;
    }

    /**
     * @return The bundleVersion
     */
    public String getBundleVersion() {
        return bundleVersion;
    }

    /**
     * @param bundleVersion The bundle_version
     */
    public void setBundleVersion(String bundleVersion) {
        this.bundleVersion = bundleVersion;
    }

    /**
     * @return The bundleShortVersion
     */
    public String getBundleShortVersion() {
        return bundleShortVersion;
    }

    /**
     * @param bundleShortVersion The bundle_short_version
     */
    public void setBundleShortVersion(String bundleShortVersion) {
        this.bundleShortVersion = bundleShortVersion;
    }

}
