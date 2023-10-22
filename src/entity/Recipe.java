package entity;

import java.util.List;

public class Recipe {
    public final String name;
    public final String url;
    public final String imagePath;
    public final double calories;
    public final int totalTime;
    public final List<Ingredient> ingredients;

    public Recipe(String name, String url, String imagePath, double calories, int totalTime, List<Ingredient> ingredients) {
        this.name = name;
        this.url = url;
        this.imagePath = imagePath;
        this.calories = calories;
        this.totalTime = totalTime;
        this.ingredients = ingredients;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getImagePath() {
        return imagePath;
    }

    public double getCalories() {
        return calories;
    }

    public int getTotalTime() {
        return totalTime;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
