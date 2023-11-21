package use_case.delete_favorite_recipe;

import entity.Recipe;
import entity.User;

public interface DeleteFavoriteRecipeDataAccessInterface {
    boolean isExists(String username, Recipe recipe);
    void saveFavoriteRecipe(String username, Recipe recipe);
    void deleteFavoriteRecipe(String username, Recipe recipe);
    void save(User user);
}

