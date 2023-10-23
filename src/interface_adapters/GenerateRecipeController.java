package interface_adapters;

import use_case.GenerateRecipeInputBoundary;
import use_case.GenerateRecipeInputData;

public class GenerateRecipeController {
    final GenerateRecipeInputBoundary generateRecipeInteractor;


    public GenerateRecipeController(GenerateRecipeInputBoundary generateRecipeInteractor) {
        this.generateRecipeInteractor = generateRecipeInteractor;
    }
    public void execute(String q, String[] diet, String[] health, String[] cuisineType, String[] mealType, String minCalories, String maxCalories, String maxPrepTime) {
        GenerateRecipeInputData generateRecipeInputData = new GenerateRecipeInputData(
                q, diet, health, cuisineType, mealType, minCalories + "-" + maxCalories, "0-" + maxPrepTime);

        generateRecipeInteractor.execute(generateRecipeInputData);
    }
}
