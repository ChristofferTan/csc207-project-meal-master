package use_case.generate_recipe;

import api.edamam.GenerateRecipeAPICaller;
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
        Recipe generatedRecipe = GenerateRecipeAPICaller.call(generateRecipeInputData).getRecipe();
        GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(generatedRecipe);
        recipeDAO.save(generatedRecipe);
        generateRecipePresenter.prepareSuccessView(generateRecipeOutputData);
    }
}
