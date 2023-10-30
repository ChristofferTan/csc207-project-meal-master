package app;

import interface_adapters.generate_recipe.GenerateRecipeController;
import interface_adapters.generate_recipe.GenerateRecipePresenter;
import use_case.generate_recipe.GenerateRecipeInputBoundary;
import use_case.generate_recipe.GenerateRecipeInteractor;
import use_case.generate_recipe.GenerateRecipeOutputBoundary;

public class GenerateRecipeFactory {
    private GenerateRecipeFactory() {}

    public static GenerateRecipeController createGenerateUseCase() {
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new GenerateRecipePresenter();

        GenerateRecipeInputBoundary generateInteractor = new GenerateRecipeInteractor(null, generateRecipeOutputBoundary);
        return new GenerateRecipeController(generateInteractor);
    }
}