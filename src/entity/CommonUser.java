package entity;

import java.util.ArrayList;

public class CommonUser implements User {
    private final String username;
    private final String password;
    private String name;
    private int age;
    private String gender;
    private int weight;
    private int height;
    private String activityLevel;
    private final ArrayList<Recipe> myRecipes;
    private final Planner planner;

    CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.myRecipes = new ArrayList<Recipe>();
        this.planner = new Planner(username);
    }
    CommonUser(String username, String password, String name, int age, String gender, int weight, int height, String activityLevel) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.activityLevel = activityLevel;
        this.myRecipes = new ArrayList<Recipe>();
        this.planner = new Planner(username);
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {return password;}

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

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
    public ArrayList<Recipe> getMyRecipes() {
        return myRecipes;
    }

    public Planner getPlanner() {
        return planner;
    }
}
