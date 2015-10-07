package net.apispark.webapi;

import net.apispark.webapi.auth.SecurityConfig;

public class Config {

    private String basePath = "https://sportstalker.apispark.net/v1";

    private final SecurityConfig securityConfig = new SecurityConfig();

    /**
     * Returns the base path of the API.
     */
    public String getBasePath() {
        return basePath;
    }

    /**
     * Define the base path of the API.
     */
    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    /**
     * Returns the security configuration used to configure the API authentication schemes.
     */
    public SecurityConfig getSecurityConfig() {
        return securityConfig;
    }

}
