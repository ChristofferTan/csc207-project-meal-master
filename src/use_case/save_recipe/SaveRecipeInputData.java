package use_case.save_recipe;

import entity.MealType;
import entity.Recipe;

import java.time.DayOfWeek;

public class SaveRecipeInputData {
    final private String username;
    final private Recipe recipe;
    final private DayOfWeek day;
    final private MealType mealType;

    public SaveRecipeInputData(String username, DayOfWeek day, MealType mealType, Recipe recipe) {
        this.username = username;
        this.day = day;
        this.mealType = mealType;
        this.recipe = recipe;
    }

    public Recipe getRecipe() {return recipe; }
    public DayOfWeek getDay() {return day; }
    public MealType getMealType() {
        return mealType;
    }
    public String getUsername() {
        return username;
    }
}