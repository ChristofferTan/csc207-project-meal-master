package app;

import interface_adapters.GenerateRecipeController;
import interface_adapters.GenerateRecipePresenter;
import use_case.GenerateRecipeInputBoundary;
import use_case.GenerateRecipeInteractor;
import use_case.GenerateRecipeOutputBoundary;

public class GenerateRecipeFactory {
    private GenerateRecipeFactory() {}

    public static GenerateRecipeController createGenerateUseCase() {
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new GenerateRecipePresenter();

        GenerateRecipeInputBoundary generateInteractor = new GenerateRecipeInteractor(null, generateRecipeOutputBoundary);
        return new GenerateRecipeController(generateInteractor);
    }
}