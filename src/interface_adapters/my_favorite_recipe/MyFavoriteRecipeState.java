package interface_adapters.my_favorite_recipe;

import entity.Recipe;

import java.util.ArrayList;

public class MyFavoriteRecipeState {
    private ArrayList<String> favoriteRecipes = new ArrayList<>();
    private String username;
    private String labelError;

    public MyFavoriteRecipeState(MyFavoriteRecipeState copy) {
        favoriteRecipes = copy.favoriteRecipes;
    }

    public MyFavoriteRecipeState() {}

    public String[] getFavoriteRecipes() {
        String[] grocery = new String[favoriteRecipes.size()];

        return favoriteRecipes.toArray(grocery);
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

    public String getLabelError() {
        return labelError;
    }

    public void setLabelError(String labelError) {
        this.labelError = labelError;
    }
}
