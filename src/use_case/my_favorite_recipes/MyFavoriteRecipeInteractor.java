package use_case.my_favorite_recipes;

import entity.User;

public class MyFavoriteRecipeInteractor implements MyFavoriteRecipeInputBoundary {
    final MyFavoriteRecipeDataAccessInterface dataAccessInterface;
    final MyFavoriteRecipeOutputBoundary myFavoriteRecipePresenter;

    public MyFavoriteRecipeInteractor(MyFavoriteRecipeDataAccessInterface dataAccessInterface, MyFavoriteRecipeOutputBoundary myFavoriteRecipeOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.myFavoriteRecipePresenter = myFavoriteRecipeOutputBoundary;
    }

    public void execute(MyFavoriteRecipeInputData myFavoriteRecipeInputData) {
        User user = dataAccessInterface.get(myFavoriteRecipeInputData.getUsername());
        MyFavoriteRecipeOutputData myFavoriteRecipeOutputData = new MyFavoriteRecipeOutputData(user);
        myFavoriteRecipePresenter.prepareSuccessView(myFavoriteRecipeOutputData);
    }
}
