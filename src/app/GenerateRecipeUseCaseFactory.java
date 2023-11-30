package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import interface_adapters.generate_recipe.GenerateRecipeController;
import interface_adapters.generate_recipe.GenerateRecipePresenter;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import use_case.generate_recipe.GenerateRecipeDataAccessInterface;
import use_case.generate_recipe.GenerateRecipeInputBoundary;
import use_case.generate_recipe.GenerateRecipeInteractor;
import use_case.generate_recipe.GenerateRecipeOutputBoundary;
import view.GenerateRecipeView;

public class GenerateRecipeUseCaseFactory {
    private GenerateRecipeUseCaseFactory() {}

    public static GenerateRecipeView create(ViewManagerModel viewManagerModel, GenerateRecipeViewModel generateRecipeViewModel, AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel, GenerateRecipeDataAccessInterface recipeDAO) {
        GenerateRecipeController generateRecipeController = createGenerateUseCase(viewManagerModel, afterGeneratedRecipeViewModel, generateRecipeViewModel, recipeDAO);
        return new GenerateRecipeView(generateRecipeController, generateRecipeViewModel, afterGeneratedRecipeViewModel, viewManagerModel);
    }

    public static GenerateRecipeController createGenerateUseCase(ViewManagerModel viewManagerModel, AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel, GenerateRecipeViewModel generateRecipeViewModel, GenerateRecipeDataAccessInterface recipeDAO) {
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new GenerateRecipePresenter(generateRecipeViewModel, afterGeneratedRecipeViewModel, viewManagerModel);

        GenerateRecipeInputBoundary generateInteractor = new GenerateRecipeInteractor(recipeDAO, generateRecipeOutputBoundary);
        return new GenerateRecipeController(generateInteractor);
    }
}