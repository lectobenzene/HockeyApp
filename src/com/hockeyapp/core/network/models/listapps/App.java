package com.hockeyapp.core.network.models.listapps;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class App {

    @Expose
    private String title;
    @SerializedName("bundle_identifier")
    @Expose
    private String bundleIdentifier;
    @SerializedName("public_identifier")
    @Expose
    private String publicIdentifier;
    @Expose
    private String platform;
    @SerializedName("release_type")
    @Expose
    private Long releaseType;
    @SerializedName("custom_release_type")
    @Expose
    private Object customReleaseType;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;
    @Expose
    private Boolean featured;
    @Expose
    private Long role;
    @Expose
    private Long id;
    @SerializedName("minimum_os_version")
    @Expose
    private String minimumOsVersion;
    @SerializedName("device_family")
    @Expose
    private Object deviceFamily;
    @Expose
    private Long status;
    @Expose
    private String visibility;
    @Expose
    private String owner;
    @SerializedName("owner_token")
    @Expose
    private String ownerToken;
    @Expose
    private String company;
    @Expose
    private String description;

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The bundleIdentifier
     */
    public String getBundleIdentifier() {
        return bundleIdentifier;
    }

    /**
     * @param bundleIdentifier The bundle_identifier
     */
    public void setBundleIdentifier(String bundleIdentifier) {
        this.bundleIdentifier = bundleIdentifier;
    }

    /**
     * @return The publicIdentifier
     */
    public String getPublicIdentifier() {
        return publicIdentifier;
    }

    /**
     * @param publicIdentifier The public_identifier
     */
    public void setPublicIdentifier(String publicIdentifier) {
        this.publicIdentifier = publicIdentifier;
    }

    /**
     * @return The platform
     */
    public String getPlatform() {
        return platform;
    }

    /**
     * @param platform The platform
     */
    public void setPlatform(String platform) {
        this.platform = platform;
    }

    /**
     * @return The releaseType
     */
    public Long getReleaseType() {
        return releaseType;
    }

    /**
     * @param releaseType The release_type
     */
    public void setReleaseType(Long releaseType) {
        this.releaseType = releaseType;
    }

    /**
     * @return The customReleaseType
     */
    public Object getCustomReleaseType() {
        return customReleaseType;
    }

    /**
     * @param customReleaseType The custom_release_type
     */
    public void setCustomReleaseType(Object customReleaseType) {
        this.customReleaseType = customReleaseType;
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
     * @return The featured
     */
    public Boolean getFeatured() {
        return featured;
    }

    /**
     * @param featured The featured
     */
    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    /**
     * @return The role
     */
    public Long getRole() {
        return role;
    }

    /**
     * @param role The role
     */
    public void setRole(Long role) {
        this.role = role;
    }

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
     * @return The minimumOsVersion
     */
    public String getMinimumOsVersion() {
        return minimumOsVersion;
    }

    /**
     * @param minimumOsVersion The minimum_os_version
     */
    public void setMinimumOsVersion(String minimumOsVersion) {
        this.minimumOsVersion = minimumOsVersion;
    }

    /**
     * @return The deviceFamily
     */
    public Object getDeviceFamily() {
        return deviceFamily;
    }

    /**
     * @param deviceFamily The device_family
     */
    public void setDeviceFamily(Object deviceFamily) {
        this.deviceFamily = deviceFamily;
    }

    /**
     * @return The status
     */
    public Long getStatus() {
        return status;
    }

    /**
     * @param status The status
     */
    public void setStatus(Long status) {
        this.status = status;
    }

    /**
     * @return The visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility The visibility
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * @return The owner
     */
    public String getOwner() {
        return owner;
    }

    /**
     * @param owner The owner
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * @return The ownerToken
     */
    public String getOwnerToken() {
        return ownerToken;
    }

    /**
     * @param ownerToken The owner_token
     */
    public void setOwnerToken(String ownerToken) {
        this.ownerToken = ownerToken;
    }

    /**
     * @return The company
     */
    public String getCompany() {
        return company;
    }

    /**
     * @param company The company
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * @return The description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

}
