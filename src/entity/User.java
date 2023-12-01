package entity;

import java.util.ArrayList;

public interface User {
    String getUsername();
    String getPassword();
    String getName();
    int getAge();
    String getGender();
    int getHeight();
    int getWeight();
    public ArrayList<String> getFavoriteRecipes();
    Planner getPlanner();

    void setName(String name);

    void setAge(int age);

    void setGender(String gender);

    void setWeight(int weight);

    void setHeight(int height);
}
