package use_case;

import api.edamam.GenerateRecipeAPIData;

public class GenerateRecipeOutputData {
    private final String label;
    private final int calories;
    private final String[] ingredients;
    private final String recipeUrl;
    private final int preparationTime;
    private final int yield;

    public GenerateRecipeOutputData(String label, int calories, String[] ingredients, String recipeUrl, int preparationTime, int yield) {
        this.label = label;
        this.calories = calories;
        this.ingredients = ingredients;
        this.recipeUrl = recipeUrl;
        this.preparationTime = preparationTime;
        this.yield  = yield;

    }

    public GenerateRecipeOutputData(GenerateRecipeAPIData generateRecipeAPIData) {
        this.label = generateRecipeAPIData.getLabel();
        this.calories = generateRecipeAPIData.getCalories();
        this.ingredients = generateRecipeAPIData.getIngredients();
        this.recipeUrl = generateRecipeAPIData.getRecipeUrl();
        this.preparationTime = generateRecipeAPIData.getPreparationTime();
        this.yield = generateRecipeAPIData.getYield();
    }

    public String getLabel() {
        return label;
    }

    public int getCalories() {
        return calories;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public int getYield() {
        return yield;
    }
}
