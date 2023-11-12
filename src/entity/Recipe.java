package entity;

import java.util.ArrayList;

public class Recipe {
    private final String label;
    private final String recipeUrl;
    private final String imagePath;
    private final int calories;
    private final ArrayList<String> ingredients;
    private final int preparationTime;
    private final int yield;

    public Recipe(String label, String recipeUrl, String imagePath, int calories, ArrayList<String> ingredients, int preparationTime, int yield) {
        this.label = label;
        this.recipeUrl = recipeUrl;
        this.imagePath = imagePath;
        this.calories = calories;
        this.ingredients = ingredients;
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

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public int getPreparationTime() {
        return preparationTime;
    }

    public int getYield() {
        return yield;
    }
}
