package app;

import interface_adapters.my_favorite_recipe.MyFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipePresenter;
import use_case.my_favorite_recipes.MyFavoriteRecipeDataAccessInterface;
import use_case.my_favorite_recipes.MyFavoriteRecipeInputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeInteractor;
import use_case.my_favorite_recipes.MyFavoriteRecipeOutputBoundary;

public class MyFavoriteRecipeFactory {
    private MyFavoriteRecipeFactory() {}

    public static MyFavoriteRecipeController createMyFavoriteRecipeUseCase(MyFavoriteRecipeDataAccessInterface dataAccessInterface) {
        MyFavoriteRecipeOutputBoundary myFavoriteRecipeOutputBoundary = new MyFavoriteRecipePresenter();


        MyFavoriteRecipeInputBoundary myFavoriteRecipeInteractor = new MyFavoriteRecipeInteractor(dataAccessInterface, myFavoriteRecipeOutputBoundary);
        return new MyFavoriteRecipeController(myFavoriteRecipeInteractor);
    }
}
