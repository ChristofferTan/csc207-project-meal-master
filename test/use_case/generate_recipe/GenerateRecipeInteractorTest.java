package use_case.generate_recipe;

import data_access.FileRecipeDataAccessObject;
import entity.RecipeFactory;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class GenerateRecipeInteractorTest {
    @Test
    void successTest() {
        GenerateRecipeInputData inputData = new GenerateRecipeInputData("chicken", new String[]{"balanced"}, new String[]{"pork-free"}, new String[]{"American"}, new String[]{"LUNCH"}, "100-1000", "600");
        GenerateRecipeDataAccessInterface repository = new FileRecipeDataAccessObject(new RecipeFactory());

        GenerateRecipeOutputBoundary successPresenter = new GenerateRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(GenerateRecipeOutputData generateRecipeOutputData) {
                assertNotNull(generateRecipeOutputData.getRecipe());
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case fail is not expected");
            }
        };

        GenerateRecipeInputBoundary interactor = new GenerateRecipeInteractor(repository, successPresenter);
        interactor.execute(inputData);
    }
}