package org.opengroup.osdu.unitservice.swagger;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "swagger")
public class SwaggerConfigurationProperties {

    private String apiTitle;
    private String apiDescription;
    private String apiVersion;
    private String contactName;
    private String contactEmail;
    private String licenseName;
    private String licenseUrl;
    private boolean apiServerFullUrlEnabled;

    public String getApiTitle() {
        return apiTitle;
    }

    public void setApiTitle(String apiTitle) {
        this.apiTitle = apiTitle;
    }

    public String getApiDescription() {
        return apiDescription;
    }

    public void setApiDescription(String apiDescription) {
        this.apiDescription = apiDescription;
    }

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getLicenseName() {
        return licenseName;
    }

    public void setLicenseName(String licenseName) {
        this.licenseName = licenseName;
    }

    public String getLicenseUrl() {
        return licenseUrl;
    }

    public void setLicenseUrl(String licenseUrl) {
        this.licenseUrl = licenseUrl;
    }

    public boolean isApiServerFullUrlEnabled() {
        return apiServerFullUrlEnabled;
    }

    public void setApiServerFullUrlEnabled(boolean apiServerFullUrlEnabled) {
        this.apiServerFullUrlEnabled = apiServerFullUrlEnabled;
    }

}
