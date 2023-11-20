package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.save_recipe.SaveRecipeController;
import interface_adapters.save_recipe.SaveRecipePresenter;
import interface_adapters.save_recipe.SaveRecipeViewModel;
import use_case.save_recipe.SaveRecipeDataAccessInterface;
import use_case.save_recipe.SaveRecipeInputBoundary;
import use_case.save_recipe.SaveRecipeInteractor;
import use_case.save_recipe.SaveRecipeOutputBoundary;
import view.SaveRecipeView;

public class SaveRecipeUseCaseFactory {
    public SaveRecipeUseCaseFactory() {}  // prevent instantiation

    public static SaveRecipeView create(ViewManagerModel viewManagerModel,
                       SaveRecipeViewModel saveRecipeViewModel,
                       SaveRecipeDataAccessInterface recipeDataAccessObject) {
        SaveRecipeController saveRecipeController = createSaveRecipeUseCase(viewManagerModel, saveRecipeViewModel, recipeDataAccessObject);
        return new SaveRecipeView(saveRecipeController, saveRecipeViewModel);
    }

    private static SaveRecipeController createSaveRecipeUseCase(ViewManagerModel viewManagerModel, SaveRecipeViewModel saveRecipeViewModel, SaveRecipeDataAccessInterface recipeDataAccessObject) {
        SaveRecipeOutputBoundary saveRecipePresenter = new SaveRecipePresenter(viewManagerModel, saveRecipeViewModel);
        SaveRecipeInputBoundary saveRecipeInteractor = new SaveRecipeInteractor(recipeDataAccessObject, saveRecipePresenter);
        return new SaveRecipeController(saveRecipeInteractor);
    }
}
