package data_access;

import entity.User;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class InMemoryUserDataAccessObject implements SignupUserDataAccessInterface, EditProfileDataAccessInterface {
    private final Map<String, User> users = new HashMap<>();
    @Override
    public boolean existsByName(String identifier) {
        return users.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public void editProfile(User user, String name, int age, String gender, double weight, double height,
                            String activityLevel, ArrayList<String> dietLabels, ArrayList<String> healthLabels) {
        user.setName(name);
        user.setAge(age);
        user.setGender(gender);
        user.setWeight(weight);
        user.setHeight(height);
        user.setActivityLevel(activityLevel);
        user.setDietLabels(dietLabels);
        user.setHealthLabels(healthLabels);
    }
}
