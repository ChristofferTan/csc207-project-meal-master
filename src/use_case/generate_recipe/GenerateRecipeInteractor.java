package use_case.generate_recipe;

import api.edamam.GenerateRecipeAPICaller;
import api.edamam.GenerateRecipeAPIData;
import entity.Recipe;

public class GenerateRecipeInteractor implements GenerateRecipeInputBoundary {
    final GenerateRecipeDataAccessInterface recipeDAO;
    final GenerateRecipeOutputBoundary generateRecipePresenter;

    public GenerateRecipeInteractor(GenerateRecipeDataAccessInterface recipeDAO,
                                    GenerateRecipeOutputBoundary generateRecipeOutputBoundary) {
        this.recipeDAO = recipeDAO;
        this.generateRecipePresenter = generateRecipeOutputBoundary;
    }

    @Override
    public void execute(GenerateRecipeInputData generateRecipeInputData) {
        GenerateRecipeAPICallerInterface generateRecipeAPICaller = new GenerateRecipeAPICaller();
        GenerateRecipeAPIData recipeAPIData = generateRecipeAPICaller.call(generateRecipeInputData);
        if (recipeAPIData == null) {
            generateRecipePresenter.prepareFailView("There is no recipe based on your preferences");
        }
        else {
            Recipe generatedRecipe = recipeAPIData.getRecipe();
            GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(generatedRecipe);
            recipeDAO.save(generatedRecipe);
            generateRecipePresenter.prepareSuccessView(generateRecipeOutputData);
        }
    }
}
