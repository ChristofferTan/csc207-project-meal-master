package interface_adapters.add_favorite_recipe;

import entity.Recipe;
import entity.User;

public class AddFavoriteRecipeState {
    private final User user;
    private final Recipe recipe;

    public AddFavoriteRecipeState(AddFavoriteRecipeState copy) {
        user = copy.user;
        recipe = copy.recipe;
    }

    public User getUser() {
        return user;
    }

    public Recipe getRecipe() {
        return recipe;
    }
}
