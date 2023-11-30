package interface_adapters.my_favorite_recipe;

import entity.Recipe;
import interface_adapters.ViewManagerModel;
import use_case.my_favorite_recipes.MyFavoriteRecipeOutputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeOutputData;

import java.util.ArrayList;

public class MyFavoriteRecipePresenter implements MyFavoriteRecipeOutputBoundary {
    private final MyFavoriteRecipeViewModel myFavoriteRecipeViewModel;
    private ViewManagerModel viewManagerModel;

    public MyFavoriteRecipePresenter(ViewManagerModel viewManagerModel,
                                     MyFavoriteRecipeViewModel myFavoriteRecipeViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.myFavoriteRecipeViewModel = myFavoriteRecipeViewModel;
    }

    @Override
    public void prepareSuccessView(MyFavoriteRecipeOutputData response) {
        MyFavoriteRecipeState myFavoriteRecipeState = myFavoriteRecipeViewModel.getState();
        myFavoriteRecipeState.setFavoriteRecipes(response.getFavoriteRecipes());
        this.myFavoriteRecipeViewModel.setState(myFavoriteRecipeState);
        this.myFavoriteRecipeViewModel.firePropertyChanged();

        this.viewManagerModel.setActiveView(myFavoriteRecipeViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
