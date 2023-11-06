package use_case.edit_profile;

import use_case.generate_recipe.GenerateRecipeInputData;

import java.util.ArrayList;

public class EditProfileInputData {
    private final String name;
    private final int age;
    private final String gender;
    private final double weight;
    private final double height;
    private final String activityLevel;
    private final ArrayList<String> dietLabels;
    private final ArrayList<String> healthLabels;

    public EditProfileInputData(String name, int age, String gender, double weight, double height, String activityLevel,
                                ArrayList<String> dietLabels, ArrayList<String> healthLabels) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
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

    public String getActivityLevel() {
        return activityLevel;
    }

    public ArrayList<String> getDietLabels() {
        return dietLabels;
    }

    public ArrayList<String> getHealthLabels() {
        return healthLabels;
    }
}
