package data_access;

import entity.User;
import use_case.add_friend.AddFriendUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, AddFriendUserDataAccessInterface {
    private final Map<String, User> accounts = new HashMap<>();

    private final Map<String, ArrayList<String>> friends = new HashMap<>();
    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public boolean isFriend(String username, String friendUsername) {
        if (!friends.containsKey(username)) {
            return false;
        }
        return friends.get(username).contains(friendUsername);
    }

    @Override
    public void addFriend(String username, String friendUsername) {
        if (!friends.containsKey(username)) {
            friends.put(username, new ArrayList<String>(Arrays.asList(friendUsername)));
        }
        else {
            friends.get(username).add(friendUsername);
        }
    }

    @Override
    public void save(User user) {
    accounts.put(user.getUsername(), user);
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }
}
