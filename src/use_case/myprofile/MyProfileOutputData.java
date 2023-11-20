package use_case.myprofile;

import entity.Recipe;
import entity.User;

import java.util.ArrayList;

public class MyProfileOutputData {
    private final User user;

    public MyProfileOutputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getName() {
        return user.getName();
    }

    public String getGender() {
        return user.getGender();
    }

    public int getAge() {
        return user.getAge();
    }

    public int getHeight() {
        return user.getHeight();
    }

    public int getWeight() {
        return user.getWeight();
    }

    public ArrayList<Recipe> getFavoriteRecipes() {
        return user.getFavoriteRecipes();
    }

}
