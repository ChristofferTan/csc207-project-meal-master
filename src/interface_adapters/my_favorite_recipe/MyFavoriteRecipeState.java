package interface_adapters.my_favorite_recipe;

import entity.Recipe;

import java.util.ArrayList;

public class MyFavoriteRecipeState {
    private ArrayList<String> favoriteRecipes;
    private String username;

    public MyFavoriteRecipeState(MyFavoriteRecipeState copy) {
        favoriteRecipes = copy.favoriteRecipes;
    }

    public MyFavoriteRecipeState() {}

    public ArrayList<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(ArrayList<String> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
