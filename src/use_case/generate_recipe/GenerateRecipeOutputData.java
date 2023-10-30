package use_case.generate_recipe;

import api.edamam.GenerateRecipeAPIData;
import entity.Ingredient;

import java.util.ArrayList;

public class GenerateRecipeOutputData {
    private final String label;
    private final String recipeUrl;
    private final String imagePath;
    private final int calories;
    private final ArrayList<Ingredient> ingredients;
    private final int preparationTime;
    private final int yield;

    public GenerateRecipeOutputData(String label, String recipeUrl, String imagePath, int calories, ArrayList<Ingredient> ingredients, int preparationTime, int yield) {
        this.label = label;
        this.recipeUrl = recipeUrl;
        this.imagePath = imagePath;
        this.calories = calories;
        this.ingredients = ingredients;
        this.preparationTime = preparationTime;
        this.yield = yield;
    }

    public GenerateRecipeOutputData(GenerateRecipeAPIData generateRecipeAPIData) {
        this.label = generateRecipeAPIData.getLabel();
        this.recipeUrl = generateRecipeAPIData.getRecipeUrl();
        this.imagePath = generateRecipeAPIData.getImagePath();
        this.calories = generateRecipeAPIData.getCalories();
        this.ingredients = generateRecipeAPIData.getIngredients();
        this.preparationTime = generateRecipeAPIData.getPreparationTime();
        this.yield = generateRecipeAPIData.getYield();
    }

    public String getLabel() {
        return label;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getCalories() {
        return calories;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public int getYield() {
        return yield;
    }
}
