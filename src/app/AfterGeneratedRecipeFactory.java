package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.add_favorite_recipe.AddFavoriteRecipeController;
import interface_adapters.add_favorite_recipe.AddFavoriteRecipePresenter;
import interface_adapters.add_favorite_recipe.AddFavoriteRecipeViewModel;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import interface_adapters.save_recipe.SaveRecipeController;
import interface_adapters.save_recipe.SaveRecipePresenter;
import interface_adapters.save_recipe.SaveRecipeViewModel;
import use_case.add_favorite_recipe.AddFavoriteRecipeInputBoundary;
import use_case.add_favorite_recipe.AddFavoriteRecipeInteractor;
import use_case.add_favorite_recipe.AddFavoriteRecipeOutputBoundary;
import use_case.add_favorite_recipe.AddFavoriteRecipeUserDataAccessInterface;
import use_case.generate_recipe.GenerateRecipeDataAccessInterface;
import use_case.save_recipe.SaveRecipeDataAccessInterface;
import use_case.save_recipe.SaveRecipeInputBoundary;
import use_case.save_recipe.SaveRecipeInteractor;
import use_case.save_recipe.SaveRecipeOutputBoundary;
import view.AfterGeneratedRecipeView;
import view.GenerateRecipeView;

import javax.swing.text.View;

public class AfterGeneratedRecipeFactory {
    public AfterGeneratedRecipeFactory() {}
    public static AfterGeneratedRecipeView create(ViewManagerModel viewManagerModel,
                                                  AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel,
                                                  GenerateRecipeViewModel generateRecipeViewModel,
                                                  SaveRecipeViewModel saveRecipeViewModel,
                                                  AddFavoriteRecipeViewModel addFavoriteRecipeViewModel,
                                                  SaveRecipeDataAccessInterface plannerDAO,
                                                  AddFavoriteRecipeUserDataAccessInterface userDAO,
                                                  GenerateRecipeDataAccessInterface recipeDAO) {
        SaveRecipeController saveRecipeController = createSaveRecipeUseCase(viewManagerModel, saveRecipeViewModel, plannerDAO);
        AddFavoriteRecipeController addFavoriteRecipeController = createAddFavoriteUseCase(viewManagerModel, addFavoriteRecipeViewModel, userDAO, recipeDAO);

        return new AfterGeneratedRecipeView(afterGeneratedRecipeViewModel, generateRecipeViewModel, saveRecipeController, addFavoriteRecipeController, viewManagerModel);
    }

    private static SaveRecipeController createSaveRecipeUseCase(ViewManagerModel viewManagerModel,
                                                                SaveRecipeViewModel saveRecipeViewModel,
                                                                SaveRecipeDataAccessInterface recipeDataAccessObject) {
        SaveRecipeOutputBoundary saveRecipePresenter = new SaveRecipePresenter(viewManagerModel, saveRecipeViewModel);
        SaveRecipeInputBoundary saveRecipeInteractor = new SaveRecipeInteractor(recipeDataAccessObject, saveRecipePresenter);
        return new SaveRecipeController(saveRecipeInteractor);
    }
    private static AddFavoriteRecipeController createAddFavoriteUseCase(ViewManagerModel viewManagerModel,
                                                                        AddFavoriteRecipeViewModel addFavoriteRecipeViewModel,
                                                                        AddFavoriteRecipeUserDataAccessInterface userDAO,
                                                                        GenerateRecipeDataAccessInterface recipeDAO) {
        AddFavoriteRecipeOutputBoundary addFavoriteRecipePresenter = new AddFavoriteRecipePresenter(addFavoriteRecipeViewModel, viewManagerModel);
        AddFavoriteRecipeInputBoundary addFavoriteRecipeInteractor = new AddFavoriteRecipeInteractor(userDAO, recipeDAO, addFavoriteRecipePresenter);
        return new AddFavoriteRecipeController(addFavoriteRecipeInteractor);
    }

}
