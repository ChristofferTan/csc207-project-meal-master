package interface_adapters.my_favorite_recipe;

import entity.Recipe;

import java.util.ArrayList;

public class MyFavoriteRecipeState {
    private ArrayList<Recipe> favoriteRecipes;

    public MyFavoriteRecipeState(MyFavoriteRecipeState copy) {
        favoriteRecipes = copy.favoriteRecipes;
    }

    public MyFavoriteRecipeState() {}

    public ArrayList<Recipe> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(ArrayList<Recipe> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
