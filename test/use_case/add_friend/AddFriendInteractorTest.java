package add_friend;

import entity.CommonUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;

public class AddFriendControllerTest{
    private CommonUser user1;
    private CommonUser user2;

    @BeforeEach
    void init() {
        user1 = new CommonUser("Christoffer", "ganteng");
        user2 = new CommonUser("Janis", "jelek");
    }
    @Test
    void getName() {
        assertEquals("Christoffer", user1.getUsername());
        assertEquals("Janis", user2.getUsername());
    }

}