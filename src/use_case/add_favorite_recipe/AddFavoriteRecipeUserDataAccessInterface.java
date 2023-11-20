package use_case.add_favorite_recipe;

import entity.Recipe;
import entity.User;

public interface AddFavoriteRecipeUserDataAccessInterface {
    boolean isExists(String username, Recipe recipe);
    void saveFavoriteRecipe(String username, Recipe recipe);
    void save(User user);
}
