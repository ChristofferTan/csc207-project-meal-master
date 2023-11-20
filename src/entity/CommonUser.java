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
    private final ArrayList<Recipe> favoriteRecipes;
    private final Planner planner;

    CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.favoriteRecipes = new ArrayList<Recipe>();
        this.planner = new Planner(username);
    }
    CommonUser(String username, String password, String name, int age, String gender, int weight, int height) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.favoriteRecipes= new ArrayList<Recipe>();
        this.activityLevel = activityLevel;
        this.dietLabels = dietLabels;
        this.healthLabels = healthLabels;
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

    public ArrayList<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public Planner getPlanner() {
        return planner;
    }
}
