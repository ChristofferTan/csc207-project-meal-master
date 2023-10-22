package use_case;

public class GenerateRecipeInputData {
    final private String q;
    final private String[] diet;
    final private String[] health;
    final private String[] cuisineType;
    final private String[] mealType;
    final private String calories;

    public GenerateRecipeInputData(String q,
                                   String[] diet,
                                   String[] health,
                                   String[] cuisineType,
                                   String[] mealType,
                                   String calories) {
        this.q = q;
        this.diet = diet;
        this.health = health;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.calories = calories;
    }

    public String getQ() {
        return q;
    }

    public String[] getDiet() {
        return diet;
    }

    public String[] getHealth() {
        return health;
    }

    public String[] getCuisineType() {
        return cuisineType;
    }

    public String[] getMealType() {
        return mealType;
    }

    public String getCalories() {
        return calories;
    }
}
