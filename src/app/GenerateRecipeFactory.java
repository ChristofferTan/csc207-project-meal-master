package app;

import interface_adapter.GenerateRecipeController;
import interface_adapter.GenerateRecipePresenter;
import use_case.GenerateRecipeInputData;
import use_case.GenerateRecipeInputBoundary;
import use_case.GenerateRecipeInteractor;
import use_case.GenerateRecipeDataAccessInterface;
import use_case.GenerateRecipeOutputBoundary;
import use_case.GenerateRecipeOutputData;

public class GenerateRecipeFactory {
    private GenerateRecipeFactory() {}

    public static GenerateRecipeController createGenerateUseCase(GenerateRecipeDataAccessInterface generateDataAccessObject) {
        GenerateRecipeOutputBoundary generateRecipeOutputBoundary = new GenerateRecipePresenter();

        GenerateRecipeInputBoundary generateInteractor = new GenerateRecipeInteractor(generateDataAccessObject, generateRecipeOutputBoundary);
        return new GenerateRecipeController(generateInteractor);
    }
}