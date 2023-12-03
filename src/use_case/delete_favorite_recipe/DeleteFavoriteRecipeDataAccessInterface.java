package use_case.delete_favorite_recipe;

import entity.User;

public interface DeleteFavoriteRecipeDataAccessInterface {
    boolean isExists(String username, String label);
    void saveFavoriteRecipe(String username, String label);
    void deleteFavoriteRecipe(String username, String label);
    void save(User user);
}

