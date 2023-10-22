package use_case;

public class GenerateRecipeOutputData {
    private final String label;
    private final int calories;
    private final String[] ingredients;
    private final String recipeUrl;

    public GenerateRecipeOutputData(String label, int calories, String[] ingredients, String recipeUrl) {
        this.label = label;
        this.calories = calories;
        this.ingredients = ingredients;
        this.recipeUrl = recipeUrl;
    }

    public String getLabel() {
        return label;
    }

    public int getCalories() {
        return calories;
    }

    public String[] getIngredients() {
        return ingredients;
    }

    public String getRecipeUrl() {
        return recipeUrl;
    }
}
