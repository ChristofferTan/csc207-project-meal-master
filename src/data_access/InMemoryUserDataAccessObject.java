package data_access;

import entity.User;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;
import use_case.add_friend.AddFriendUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, AddFriendUserDataAccessInterface, EditProfileDataAccessInterface {
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
        public void editProfile(User user, String name, int age, String gender, double weight, double height) {
//            user.setName(name);
//            user.setAge(age);
//            user.setGender(gender);
//            user.setWeight(weight);
//            user.setHeight(height);
//            TODO: Change edit method to directly edit csv file
        }

        @Override
        public User get(String username) {
            return accounts.get(username);
        }
    }
