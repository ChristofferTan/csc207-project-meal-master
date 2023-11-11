package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class CommonUserTest {
    private CommonUser user;

    @BeforeEach
    void init() {
        user = new CommonUser("Razan", "bocil");
    }

    @Test
    void getNameTest() {
        assertEquals("Razan", user.getUsername());
    }

    @Test
    void getPasswordTest() {
        assertEquals("bocil", user.getPassword());
    }

}