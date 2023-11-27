package interface_adapters.after_generated_recipe;

import entity.MealType;
import entity.Recipe;

import java.time.DayOfWeek;

public class AfterGeneratedRecipeState {
    private String username = "";
    private Recipe recipe;
    private MealType mealType;
    private DayOfWeek dayInPlanner;

    public AfterGeneratedRecipeState(AfterGeneratedRecipeState copy) {
        username = copy.username;
        recipe = copy.recipe;
        mealType = copy.mealType;
        dayInPlanner = copy.dayInPlanner;
    }
    public AfterGeneratedRecipeState() {};

    public String getUsername() {return username;}
    public void setUsername(String username) {this.username = username;}

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public MealType getMealType() {
        return mealType;
    }

    public void setMealType(MealType mealType) {
        this.mealType = mealType;
    }

    public DayOfWeek getDayInPlanner() {
        return dayInPlanner;
    }

    public void setDayInPlanner(DayOfWeek dayInPlanner) {
        this.dayInPlanner = dayInPlanner;
    }
}
