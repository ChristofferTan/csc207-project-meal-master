package api.edamam;

public class GenerateRecipeAPIData {
    private final String label;
    private final int calories;
    private final String[] ingredients;
    private final String recipeUrl;
    private final int preparationTime;

    public GenerateRecipeAPIData(String label, int calories, String[] ingredients, String recipeUrl, int preparationTime) {
        this.label = label;
        this.calories = calories;
        this.ingredients = ingredients;
        this.recipeUrl = recipeUrl;
        this.preparationTime = preparationTime;
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

    public int getPreparationTime() {
        return preparationTime;
    }
}
