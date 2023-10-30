package use_case.save_recipe;

import entity.Recipe;

public class SaveRecipeInputData {
    final private Recipe recipe;
    final private String day;
    final private String mealType;

    public SaveRecipeInputData(Recipe recipe, String day, String mealType) {
        this.recipe = recipe;
        this.day = day;
        this.mealType = mealType;
    }
    public Recipe getRecipe() {return recipe; }
    public String getDay() {return day; }
    public String getMealType() {
        return mealType;
    }

}