package use_case.my_favorite_recipes;

import java.util.ArrayList;

public class MyFavoriteRecipeInteractor implements MyFavoriteRecipeInputBoundary {
    final MyFavoriteRecipeDataAccessInterface dataAccessInterface;
    final MyFavoriteRecipeOutputBoundary myFavoriteRecipePresenter;

    public MyFavoriteRecipeInteractor(MyFavoriteRecipeDataAccessInterface dataAccessInterface, MyFavoriteRecipeOutputBoundary myFavoriteRecipeOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.myFavoriteRecipePresenter = myFavoriteRecipeOutputBoundary;
    }

    public void execute(MyFavoriteRecipeInputData myFavoriteRecipeInputData) {
        ArrayList<String> favoriteRecipes = dataAccessInterface.get(myFavoriteRecipeInputData.getUsername()).getFavoriteRecipes();
        MyFavoriteRecipeOutputData myFavoriteRecipeOutputData = new MyFavoriteRecipeOutputData(favoriteRecipes);
        myFavoriteRecipePresenter.prepareSuccessView(myFavoriteRecipeOutputData);
    }
}
