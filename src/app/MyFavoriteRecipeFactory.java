package app;

import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import interface_adapters.ViewManagerModel;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipeController;
import interface_adapters.delete_favorite_recipe.DeleteFavoriteRecipePresenter;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeController;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipePresenter;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeViewModel;
import interface_adapters.save_recipe.SaveRecipeController;
import interface_adapters.save_recipe.SaveRecipePresenter;
import interface_adapters.save_recipe.SaveRecipeViewModel;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeDataAccessInterface;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeInputBoundary;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeInteractor;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeOutputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeDataAccessInterface;
import use_case.my_favorite_recipes.MyFavoriteRecipeInputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeInteractor;
import use_case.my_favorite_recipes.MyFavoriteRecipeOutputBoundary;
import use_case.save_recipe.SaveRecipeInputBoundary;
import use_case.save_recipe.SaveRecipeInteractor;
import use_case.save_recipe.SaveRecipeOutputBoundary;
import view.MyFavoriteRecipeView;

public class MyFavoriteRecipeFactory {
    private MyFavoriteRecipeFactory() {}

    public static MyFavoriteRecipeView create(ViewManagerModel viewManagerModel, MyFavoriteRecipeViewModel myFavoriteRecipeViewModel,
                                              FileUserDataAccessObject userDataAccessObject, FileRecipeDataAccessObject fileRecipeDataAccessObject,
                                              SaveRecipeViewModel saveRecipeViewModel, FilePlannerDataAccessObject filePlannerDataAccessObject) {
        MyFavoriteRecipeController myFavoriteRecipeController = createMyFavoriteRecipeUseCase(viewManagerModel, myFavoriteRecipeViewModel, userDataAccessObject);
        SaveRecipeController saveRecipeController = createSaveFavoriteRecipeUseCase(viewManagerModel, saveRecipeViewModel, filePlannerDataAccessObject);
        DeleteFavoriteRecipeController deleteFavoriteRecipeController = createDeleteFavoriteRecipeUseCase(myFavoriteRecipeViewModel, userDataAccessObject, fileRecipeDataAccessObject);
        return new MyFavoriteRecipeView(myFavoriteRecipeController, myFavoriteRecipeViewModel, saveRecipeController, deleteFavoriteRecipeController, viewManagerModel, fileRecipeDataAccessObject);
    }

    private static MyFavoriteRecipeController createMyFavoriteRecipeUseCase(ViewManagerModel viewManagerModel, MyFavoriteRecipeViewModel myFavoriteRecipeViewModel, MyFavoriteRecipeDataAccessInterface dataAccessInterface) {
        MyFavoriteRecipeOutputBoundary myFavoriteRecipeOutputBoundary = new MyFavoriteRecipePresenter(viewManagerModel, myFavoriteRecipeViewModel);

        MyFavoriteRecipeInputBoundary myFavoriteRecipeInteractor = new MyFavoriteRecipeInteractor(dataAccessInterface, myFavoriteRecipeOutputBoundary);
        return new MyFavoriteRecipeController(myFavoriteRecipeInteractor);
    }

    public static SaveRecipeController createSaveFavoriteRecipeUseCase(ViewManagerModel viewManagerModel, SaveRecipeViewModel saveRecipeViewModel, FilePlannerDataAccessObject filePlannerDataAccessObject) {
        SaveRecipeOutputBoundary saveRecipeOutputBoundary = new SaveRecipePresenter(viewManagerModel, saveRecipeViewModel);

        SaveRecipeInputBoundary saveRecipeInteractor = new SaveRecipeInteractor(filePlannerDataAccessObject, saveRecipeOutputBoundary);
        return new SaveRecipeController(saveRecipeInteractor);
    }

    public static DeleteFavoriteRecipeController createDeleteFavoriteRecipeUseCase(MyFavoriteRecipeViewModel myFavoriteRecipeViewModel, DeleteFavoriteRecipeDataAccessInterface dataAccessInterface, FileRecipeDataAccessObject fileRecipeDataAccessObject) {
        DeleteFavoriteRecipeOutputBoundary deleteFavoriteRecipeOutputBoundary = new DeleteFavoriteRecipePresenter(myFavoriteRecipeViewModel);


        DeleteFavoriteRecipeInputBoundary deleteFavoriteRecipeInteractor = new DeleteFavoriteRecipeInteractor(dataAccessInterface, fileRecipeDataAccessObject, deleteFavoriteRecipeOutputBoundary);
        return new DeleteFavoriteRecipeController(deleteFavoriteRecipeInteractor);
    }
}
