package use_case.myprofile;

import entity.Recipe;
import entity.User;

import java.util.ArrayList;
import java.util.Objects;

public class MyProfileOutputData {
    private final User user;

    public MyProfileOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getName();
    }

    public String getGender() {
        return user.getGender();
    }

    public int getAge() {
        return user.getAge();
    }

    public int getHeight() {
        return user.getHeight();
    }

    public int getWeight() {
        return user.getWeight();
    }

    public int getRecommendedCalories() {
        int weight = user.getWeight();
        int height = user.getHeight();
        String gender = user.getGender();
        int age = user.getAge();

        if (gender.equals("Man")) {
            return (int) Math.round(66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age));
        } else if (gender.equals("Woman")){
            return (int) Math.round(655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age));
        } else {
            return (int) (Math.round(66.47 + (13.75 * weight) + (5.003 * height) - (6.755 * age)) + Math.round(655.1 + (9.563 * weight) + (1.85 * height) - (4.676 * age)) / 2);
        }
    }
}
