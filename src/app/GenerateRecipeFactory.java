package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.generate_recipe.GenerateRecipeController;
import interface_adapters.generate_recipe.GenerateRecipePresenter;
import interface_adapters.generate_recipe.GenerateRecipeViewModel;
import use_case.generate_recipe.GenerateRecipeInputBoundary;
import use_case.generate_recipe.GenerateRecipeInteractor;
import use_case.generate_recipe.GenerateRecipeOutputBoundary;
import view.GenerateRecipeView;

public class GenerateRecipeFactory {
    private GenerateRecipeFactory() {}

    public static GenerateRecipeView create(ViewManagerModel viewManagerModel, GenerateRecipeViewModel generateRecipeViewModel) {
        GenerateRecipeController generateRecipeController = createGenerateUseCase(viewManagerModel, generateRecipeViewModel);
        return new GenerateRecipeView(generateRecipeController, generateRecipeViewModel);
    }

    public static GenerateRecipeController createGenerateUseCase(ViewManagerModel viewManagerModel, GenerateRecipeViewModel generateRecipeViewModel) {
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new GenerateRecipePresenter(generateRecipeViewModel, viewManagerModel);

        GenerateRecipeInputBoundary generateInteractor = new GenerateRecipeInteractor(null, generateRecipeOutputBoundary);
        return new GenerateRecipeController(generateInteractor);
    }
}