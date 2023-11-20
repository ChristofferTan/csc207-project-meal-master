package use_case.edit_profile;

import entity.User;

import java.util.ArrayList;

public class EditProfileInputData {
    private final String username;
    private final String name;
    private final int age;
    private final String gender;
    private final double weight;
    private final double height;

    public EditProfileInputData(String username, String name, int age, String gender, double weight, double height) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    public String getUsername() {
        return username;
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
