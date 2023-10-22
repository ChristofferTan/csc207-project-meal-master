package entity;

import java.util.ArrayList;

public class Recipe {
    private final String name;
    private final String url;
    private final String imagePath;
    private final double calories;
    private final int totalTime;
    private final ArrayList<Ingredient> ingredients;

    public Recipe(String name, String url, String imagePath, double calories, int totalTime, ArrayList<Ingredient> ingredients) {
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

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }
}
