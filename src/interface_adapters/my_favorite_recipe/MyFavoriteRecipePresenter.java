package interface_adapters.my_favorite_recipe;

import entity.Recipe;
import use_case.my_favorite_recipes.MyFavoriteRecipeOutputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeOutputData;

import java.util.ArrayList;

public class MyFavoriteRecipePresenter implements MyFavoriteRecipeOutputBoundary {
    public MyFavoriteRecipePresenter() {

    }

    @Override
    public void prepareSuccessView(MyFavoriteRecipeOutputData myFavoriteRecipeOutputData) {
        ArrayList<Recipe> favoriteRecipes = myFavoriteRecipeOutputData.getFavoriteRecipes();
        int favoriteRecipeCounter = 1;
        for (Recipe recipe : favoriteRecipes) {
            System.out.println("Favorite Recipe #" + favoriteRecipeCounter);
            System.out.println("-".repeat(35));
            favoriteRecipeCounter++;
            System.out.println("Label: " + recipe.getLabel());
            System.out.println("Image URL: " + recipe.getImagePath());
            System.out.println("Number of serving(s): " + recipe.getYield());
            System.out.println("Calories (per serving): " + recipe.getCalories());
            ArrayList<String> ingredients = recipe.getIngredients();
            System.out.println("Ingredients:");
            for (String ingredient : ingredients) {
                System.out.println('\t' + ingredient);
            }
            System.out.println("Recipe URL: " + recipe.getRecipeUrl());
            System.out.println("Preparation Time: " + recipe.getPreparationTime());
            System.out.println("-".repeat(35));
        }
        System.out.print("\n");
    }

    @Override
    public void prepareFailView(String error) {
        System.out.println(error);
    }


}
