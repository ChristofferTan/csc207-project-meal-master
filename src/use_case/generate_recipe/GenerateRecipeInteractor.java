package use_case.generate_recipe;

import api.edamam.GenerateRecipeAPICaller;
import entity.Recipe;

public class GenerateRecipeInteractor implements GenerateRecipeInputBoundary {
    final GenerateRecipeDataAccessInterface dataAccessInterface;
    final GenerateRecipeOutputBoundary generateRecipePresenter;

    public GenerateRecipeInteractor(GenerateRecipeDataAccessInterface dataAccessInterface,
                                    GenerateRecipeOutputBoundary generateRecipeOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.generateRecipePresenter = generateRecipeOutputBoundary;
    }

    @Override
    public void execute(GenerateRecipeInputData generateRecipeInputData) {
        Recipe generatedRecipe = GenerateRecipeAPICaller.call(generateRecipeInputData).getRecipe();
        GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(generatedRecipe);
        dataAccessInterface.save(generatedRecipe);
        generateRecipePresenter.prepareSuccessView(generateRecipeOutputData);
    }
}
