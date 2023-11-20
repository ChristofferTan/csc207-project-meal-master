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

}
