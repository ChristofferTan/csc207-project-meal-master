package entity;

import java.util.ArrayList;

public class User {
    private final String username;
    private String name;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private String activityLevel;
    private ArrayList<String> dietLabels;
    private ArrayList<String> healthLabels;
    private final ArrayList<Recipe> myRecipes;
    private final Planner planner;

    User(String username, String name, int age, String gender, double weight, double height, String activityLevel,
         ArrayList<String> dietLabels, ArrayList<String> healthLabels, ArrayList<Recipe> myRecipes, Planner planner) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
        this.myRecipes = myRecipes;
        this.planner = planner;
    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }

    public ArrayList<String> getDietLabels() {
        return dietLabels;
    }

    public void setDietLabels(ArrayList<String> dietLabels) {
        this.dietLabels = dietLabels;
    }

    public ArrayList<String> getHealthLabels() {
        return healthLabels;
    }

    public void setHealthLabels(ArrayList<String> healthLabels) {
        this.healthLabels = healthLabels;
    }

    public ArrayList<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public Planner getPlanner() {
        return planner;
    }

}
