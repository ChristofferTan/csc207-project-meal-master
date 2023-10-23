package use_case;

public class GenerateRecipeInputData {
    final private String q;
    final private String[] diet;
    final private String[] health;
    final private String[] cuisineType;
    final private String[] mealType;
    final private String calories;  // format: {MIN}-{MAX}
    final private String preparationTime;  // format: 0-{MAX}

    public GenerateRecipeInputData(String q,
                                   String[] diet,
                                   String[] health,
                                   String[] cuisineType,
                                   String[] mealType,
                                   String calories,
                                   String preparationTime) {
        this.q = q;
        this.diet = diet;
        this.health = health;
        this.cuisineType = cuisineType;
        this.mealType = mealType;
        this.calories = calories;
        this.preparationTime = preparationTime;
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

    public String getPreparationTime() {
        return preparationTime;
    }
}
