package app;

import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeController;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipePresenter;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeViewModel;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipePresenter;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeViewModel;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeDataAccessInterface;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeInputBoundary;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeInteractor;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeDataAccessInterface;
import use_case.my_favorite_recipes.MyFavoriteRecipeInputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeInteractor;
import use_case.my_favorite_recipes.MyFavoriteRecipeOutputBoundary;
import view.MyFavoriteRecipeView;

public class MyFavoriteRecipeFactory {
    private MyFavoriteRecipeFactory() {}

    public static MyFavoriteRecipeView create(ViewManagerModel viewManagerModel,
                                              MyFavoriteRecipeViewModel myFavoriteRecipeViewModel, DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel,
                                              FileUserDataAccessObject userDataAccessObject, FileRecipeDataAccessObject recipeDataAccessObject) {

        MyFavoriteRecipeController myFavoriteRecipeController = createMyFavoriteRecipeUseCase(viewManagerModel, myFavoriteRecipeViewModel, userDataAccessObject);
        DeleteFavoriteRecipeController deleteFavoriteRecipeController = createDeleteFavoriteRecipeUseCase(viewManagerModel, deleteFavoriteRecipeViewModel, userDataAccessObject, recipeDataAccessObject);
        return new MyFavoriteRecipeView(myFavoriteRecipeController, myFavoriteRecipeViewModel, deleteFavoriteRecipeController, deleteFavoriteRecipeViewModel, viewManagerModel);
    }

    private static MyFavoriteRecipeController createMyFavoriteRecipeUseCase(ViewManagerModel viewManagerModel, MyFavoriteRecipeViewModel myFavoriteRecipeViewModel, MyFavoriteRecipeDataAccessInterface dataAccessInterface) {
        MyFavoriteRecipeOutputBoundary myFavoriteRecipeOutputBoundary = new MyFavoriteRecipePresenter(viewManagerModel, myFavoriteRecipeViewModel);

        MyFavoriteRecipeInputBoundary myFavoriteRecipeInteractor = new MyFavoriteRecipeInteractor(dataAccessInterface, myFavoriteRecipeOutputBoundary);
        return new MyFavoriteRecipeController(myFavoriteRecipeInteractor);
    }

    private static DeleteFavoriteRecipeController createDeleteFavoriteRecipeUseCase(ViewManagerModel viewManagerModel, DeleteFavoriteRecipeViewModel deleteFavoriteRecipeViewModel, DeleteFavoriteRecipeDataAccessInterface dataAccessInterface, FileRecipeDataAccessObject fileRecipeDataAccessObject) {
        DeleteFavoriteRecipeOutputBoundary deleteFavoriteRecipeOutputBoundary = new DeleteFavoriteRecipePresenter(viewManagerModel, deleteFavoriteRecipeViewModel);


        DeleteFavoriteRecipeInputBoundary deleteFavoriteRecipeInteractor = new DeleteFavoriteRecipeInteractor(dataAccessInterface, fileRecipeDataAccessObject, deleteFavoriteRecipeOutputBoundary);
        return new DeleteFavoriteRecipeController(deleteFavoriteRecipeInteractor);
    }
}
