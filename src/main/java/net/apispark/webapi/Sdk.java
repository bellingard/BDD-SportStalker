package net.apispark.webapi;

/**
 * Entry-point for API calls.
 */
public class Sdk {

    private final net.apispark.webapi.Config config = new net.apispark.webapi.Config();

    public Sdk() {
    }

    /**
     * Returns the SDK configuration.
     */
    public net.apispark.webapi.Config getConfig() {
        return config;
    }

    public net.apispark.webapi.client.UserListClientResource userList() {
        return new net.apispark.webapi.client.UserListClientResource(config);
    }

    public net.apispark.webapi.client.UserClientResource user(java.lang.String userid) {
        return new net.apispark.webapi.client.UserClientResource(config, userid);
    }

}
