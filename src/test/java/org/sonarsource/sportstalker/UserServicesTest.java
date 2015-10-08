package org.sonarsource.sportstalker;

import net.apispark.webapi.representation.User;
import org.junit.AfterClass;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.*;

public class UserServicesTest {

    private static UserServices userServices = UserServices.INSTANCE;
    private static ArrayList<String> fakeUserIdList = new ArrayList<String>();

    @AfterClass
    public static void cleanup_fake_users() {
        userServices.log("Cleaning Fake users...");
        for (String userId : fakeUserIdList) {
            userServices.unregisterUser(userId);
        }
    }

    @Test
    public void should_not_locate_unexisting_user() {
        User foundUser = userServices.locateUser("Unexisting user");
        assertThat(foundUser).isNull();
    }

    @Test
    public void should_register_new_user() {
        String randomUserName = generateRandomUserName();
        String userId = registerNewUser(randomUserName);
        assertThat(userId).isNotNull();

        User foundUser = userServices.locateUser(randomUserName);
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo(randomUserName);
        assertThat(foundUser.getLatitude()).isNull();
        assertThat(foundUser.getLongitude()).isNull();
    }

    @Test
    public void should_update_user_position() {
        String randomUserName = generateRandomUserName();
        String userId = registerNewUser(randomUserName);
        userServices.updatePosition(userId, "12.345", "67.89");

        User foundUser = userServices.locateUser(randomUserName);
        assertThat(foundUser.getLatitude()).isEqualTo("12.345");
        assertThat(foundUser.getLongitude()).isEqualTo("67.89");
    }

    @Test
    public void should_unregister_user() {
        String randomUserName = generateRandomUserName();
        String userId = registerNewUser(randomUserName);
        assertThat(userId).isNotNull();

        userServices.unregisterUser(userId);

        User foundUser = userServices.locateUser(randomUserName);
        assertThat(foundUser).isNull();
        fakeUserIdList.remove(userId);
    }

    private String generateRandomUserName() {
        return "Fake-User " + Math.random();
    }

    private String registerNewUser(String randomUserName) {
        String userId = userServices.registerNewUser(randomUserName);
        fakeUserIdList.add(userId);
        return userId;
    }

}