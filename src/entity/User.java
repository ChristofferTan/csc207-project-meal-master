package entity;

import java.util.List;

public class User {
    private final String username;
    private String name;
    private int age;
    private String gender;
    private double weight;
    private double height;
    private String activityLevel;
    private List<String> dietLabels;
    private List<String> healthLabels;
    private List<Recipe> myRecipes;
    private Planner planner;

    User(String username, String name, int age, String gender, double weight, double height, String activityLevel,
         List<String> dietLabels, List<String> healthLabels, List<Recipe> myRecipes, Planner planner) {
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
}
