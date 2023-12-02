package use_case.grocery_list;

import entity.MealType;
import entity.Planner;
import entity.Recipe;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

public class GroceryListOutputData {
    private final Planner planner;

    public GroceryListOutputData(Planner planner) {
        this.planner = planner;
    }

    public Planner getPlanner() {
        return planner;
    }

    public String getUsername() {
        return planner.getUsername();
    }

    public ArrayList<String> getGroceryList() {
        if (planner == null) {
            return null;
        }
        ArrayList<String> groceryList = new ArrayList<>();
        for (HashMap<MealType, Recipe> recipes: planner.getWeeklyRecipes().values()) {
            for (Recipe recipe: recipes.values()) {
                if (recipe != null) {
                    System.out.println(recipe.getLabel());
                    for (String ingredient: recipe.getIngredients()) {
                        groceryList.add(ingredient);
                    }
                }
            }
        }
        return groceryList;
    }
}
