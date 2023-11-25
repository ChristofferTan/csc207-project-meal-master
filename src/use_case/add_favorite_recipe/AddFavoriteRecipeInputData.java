package use_case.add_favorite_recipe;

import entity.Recipe;

public class AddFavoriteRecipeInputData {
    final private String username;
    final private String label;

    public AddFavoriteRecipeInputData(String username, String label) {
        this.username = username;
        this.label = label;
    }

    public String getUsername() {
        return username;
    }

    public String getLabel() {
        return label;
    }
}
