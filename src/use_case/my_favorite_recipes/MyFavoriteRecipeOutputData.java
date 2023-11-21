package use_case.my_favorite_recipes;

import entity.Recipe;
import entity.User;

import java.util.ArrayList;

public class MyFavoriteRecipeOutputData {
    private final User user;

    public MyFavoriteRecipeOutputData(User user) {
        this.user = user;
    }

    public ArrayList<Recipe> getFavoriteRecipes() {
        return user.getFavoriteRecipes();
    }
}
