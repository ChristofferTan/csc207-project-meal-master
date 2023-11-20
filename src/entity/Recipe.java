package entity;

import java.util.ArrayList;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Recipe recipe = (Recipe) o;
        return calories == recipe.calories && preparationTime == recipe.preparationTime && yield == recipe.yield && Objects.equals(label, recipe.label) && Objects.equals(recipeUrl, recipe.recipeUrl) && Objects.equals(imagePath, recipe.imagePath) && Objects.equals(ingredients, recipe.ingredients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(label, recipeUrl, imagePath, calories, ingredients, preparationTime, yield);
    }
}
