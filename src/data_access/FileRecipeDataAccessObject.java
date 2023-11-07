package data_access;

import entity.MealType;
import entity.Recipe;
import entity.RecipeFactory;
import use_case.generate_recipe.GenerateRecipeDataAccessInterface;
import use_case.save_recipe.SaveRecipeDataAccessInterface;
import java.time.DayOfWeek;


public class FileRecipeDataAccessObject implements GenerateRecipeDataAccessInterface, SaveRecipeDataAccessInterface {

    @Override
    public void save(DayOfWeek day, MealType mealType, Recipe recipe) {

    }

    @Override
    public Recipe get(DayOfWeek day, MealType mealType) {
        return null;
    }
}
