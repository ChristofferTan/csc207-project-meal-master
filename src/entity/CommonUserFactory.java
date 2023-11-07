package entity;

import java.util.ArrayList;

public class CommonUserFactory implements UserFactory{
    @Override
    public User create(String username, String password) {
        return new CommonUser(username, password);
    }

    public User create(String username, String password, String name, int age, String gender, double weight, double height, String activityLevel,
                             ArrayList<String> dietLabels, ArrayList<String> healthLabels) {
        return new CommonUser(username, password, name, age, gender, weight, height, activityLevel, dietLabels, healthLabels);
    }
}
