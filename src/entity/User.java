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
    public ArrayList<Recipe> getFavoriteRecipes();

    void setName(String name);

    void setAge(int age);

    void setGender(String gender);

    void setWeight(double weight);

    void setHeight(double height);
}
