package interface_adapters.generate_recipe;

import use_case.generate_recipe.GenerateRecipeInputBoundary;
import use_case.generate_recipe.GenerateRecipeInputData;

public class GenerateRecipeController {
    final GenerateRecipeInputBoundary generateRecipeInteractor;


    public GenerateRecipeController(GenerateRecipeInputBoundary generateRecipeInteractor) {
        this.generateRecipeInteractor = generateRecipeInteractor;
    }
    public void execute(String q, String[] diet, String[] health, String[] cuisineType, String[] mealType,
                        String minCalories, String maxCalories, String maxPreparationTime) {
        GenerateRecipeInputData generateRecipeInputData = new GenerateRecipeInputData(
                q, diet, health, cuisineType, mealType, minCalories + "-" + maxCalories, "0-" + maxPreparationTime);

        generateRecipeInteractor.execute(generateRecipeInputData);
    }
}
