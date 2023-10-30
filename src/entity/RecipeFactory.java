package entity;

import java.util.ArrayList;

public class RecipeFactory {
    public static Recipe create(String label, String recipeUrl, String imagePath, int calories,
                         ArrayList<Ingredient> ingredients, int preparationTime, int yield) {
        return new Recipe(label, recipeUrl, imagePath, calories, ingredients, preparationTime, yield);
    };
}
