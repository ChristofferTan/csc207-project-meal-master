package use_case.my_favorite_recipes;

import entity.User;

public interface MyFavoriteRecipeDataAccessInterface {
    User get(String username);
}