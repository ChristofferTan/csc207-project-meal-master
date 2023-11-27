package app;

import interface_adapters.ViewManagerModel;
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

    public static GenerateRecipeView create(ViewManagerModel viewManagerModel, GenerateRecipeViewModel generateRecipeViewModel, GenerateRecipeDataAccessInterface generateRecipeDataAccessObject) {
        GenerateRecipeController generateRecipeController = createGenerateUseCase(viewManagerModel, generateRecipeViewModel, generateRecipeDataAccessObject);
        return new GenerateRecipeView(generateRecipeController, generateRecipeViewModel);
    }

    public static GenerateRecipeController createGenerateUseCase(ViewManagerModel viewManagerModel, GenerateRecipeViewModel generateRecipeViewModel, GenerateRecipeDataAccessInterface generateRecipeDataAccessObject) {
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new GenerateRecipePresenter(generateRecipeViewModel, viewManagerModel);

        GenerateRecipeInputBoundary generateInteractor = new GenerateRecipeInteractor(generateRecipeDataAccessObject, generateRecipeOutputBoundary);
        return new GenerateRecipeController(generateInteractor);
    }
}