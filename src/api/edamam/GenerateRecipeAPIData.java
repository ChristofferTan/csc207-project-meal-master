package api.edamam;

import entity.Ingredient;

import java.util.ArrayList;

public class GenerateRecipeAPIData {
    private final String label;
    private final String recipeUrl;
    private final String imagePath;
    private final int calories;
    private final ArrayList<Ingredient> ingredients;
    private final int preparationTime;
    private final int yield;

    public GenerateRecipeAPIData(String label, int calories, ArrayList<Ingredient> ingredients, String imagePath,
                                 String recipeUrl, int preparationTime, int yield) {
        this.label = label;
        this.calories = calories;
        this.ingredients = ingredients;
        this.imagePath = imagePath;
        this.recipeUrl = recipeUrl;
        this.preparationTime = preparationTime;
        this.yield = yield;
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
