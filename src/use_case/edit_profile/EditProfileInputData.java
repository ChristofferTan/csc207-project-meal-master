package use_case.edit_profile;

import entity.User;

import java.util.ArrayList;

public class EditProfileInputData {
    private final User user;
    private final String name;
    private final int age;
    private final String gender;
    private final double weight;
    private final double height;

    public EditProfileInputData(User user, String name, int age, String gender, double weight, double height) {
        this.user = user;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public User getUser() {
        return user;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
}
