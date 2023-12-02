package use_case.my_favorite_recipes;

import java.util.ArrayList;

public class MyFavoriteRecipeOutputData {
    private ArrayList<String> favoriteRecipes;

    public MyFavoriteRecipeOutputData(ArrayList<String> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }

    public ArrayList<String> getFavoriteRecipes() {
        return favoriteRecipes;
    }

    public void setFavoriteRecipes(ArrayList<String> favoriteRecipes) {
        this.favoriteRecipes = favoriteRecipes;
    }
}
