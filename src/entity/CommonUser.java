package entity;

import java.util.ArrayList;

public class CommonUser implements User {
    private final PlannerFactory plannerFactory = new PlannerFactory();
    private final String username;
    private final String password;
    private String name;
    private int age;
    private String gender;
    private int weight;
    private int height;
    private final ArrayList<String> favoriteRecipes;
    private final Planner planner;

    CommonUser(String username, String password) {
        this.username = username;
        this.password = password;
        this.favoriteRecipes = new ArrayList<String>();
        this.planner = plannerFactory.create(username);
    }
    CommonUser(String username, String password, String name, int age, String gender, int weight, int height, Planner planner) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.favoriteRecipes= new ArrayList<String>();
        this.planner = planner;
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

    public ArrayList<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public Planner getPlanner() {
        return planner;
    }
}
