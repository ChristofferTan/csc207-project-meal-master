package use_case.add_favorite_recipe;

import entity.Recipe;
import entity.User;

public interface AddFavoriteRecipeUserDataAccessInterface {
    boolean isExists(String username, String label);
    void saveFavoriteRecipe(String username, String label);
    void save(User user);
}
