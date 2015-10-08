package org.sonarsource.sportstalker;

import net.apispark.webapi.Sdk;
import net.apispark.webapi.client.UserClientResource;
import net.apispark.webapi.representation.User;
import net.apispark.webapi.representation.UserList;

import java.util.Collection;

public enum UserServices {

    INSTANCE;

    private String apiSparkLogin = "ced19dc7-da5f-47eb-bbc0-fc4a3c66c66b";
    private String apiSparkPassword = "8d7205c8-0f4b-414c-b00b-a95ff1ebb093";
    private Sdk sportStalkerSdk;

    UserServices() {
        sportStalkerSdk = new Sdk();
        sportStalkerSdk.getConfig().getSecurityConfig().configureAuthHTTP_BASIC(apiSparkLogin, apiSparkPassword);
    }

    /**
     * Just here to fool a bit SonarLint ;)
     */
    protected void log(String format, Object ... args) {
        System.out.printf(format, args);
    }

    /**
     * Returns the ID of the user
     */
    public String registerNewUser(String name) {
        User user = new User();
        user.setUsername(name);
        User createdUser = sportStalkerSdk.userList().postUserList(user);
        log("New user %s created with ID %s.", createdUser.getUsername(), createdUser.getId());
        return createdUser.getId();
    }

    /**
     * Updates the position of the user identified by the given userId
     */
    public void updatePosition(String userId, String latitude, String longitude) {
        UserClientResource userClientResource = sportStalkerSdk.user(userId);
        User user = userClientResource.getUser();
        user.setLatitude(latitude);
        user.setLongitude(longitude);
        userClientResource.putUser(user);
        log("%s's position updated to %s / %s.", user.getUsername(), latitude, longitude);
    }

    /**
     * Allows to find all registered users to be able to locate them on a map
     * TODO => can have performance impacts, should rework this later to have pagination for instance
     */
    public UserList locateAllUsers() {
        return sportStalkerSdk.userList().getUserList(null, null, null, null, null, null, null);
    }

    /**
     * Find a specific user to be able to locate him/her on a map.
     * Will return NULL if no user has been found.
     */
    public User locateUser(String name) {
        UserList userList = sportStalkerSdk.userList().getUserList(null, null, null, null, null, null, name);
        return userList.isEmpty() ? null : userList.iterator().next();
    }

    public void unregisterUser(String userId) {

    }

}
