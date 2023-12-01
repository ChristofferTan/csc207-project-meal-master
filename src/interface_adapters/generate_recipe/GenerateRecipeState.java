package interface_adapters.generate_recipe;

import java.util.Arrays;

public class GenerateRecipeState {
    private String username = "";
    private String keyword = "";
    private String keywordError = null;
    private String[] diet = new String[1];
    private String[] health = new String[1];
    private String[] cuisineType = new String[1];
    private String[] mealType = new String[1];
    private String minCalories = "";
    private String maxCalories = "";
    private String maxPreparationTime = "";

    public GenerateRecipeState(GenerateRecipeState copy) {
        username = copy.username;
        keyword = copy.keyword;
        copy.keyword = copy.keyword;
        diet = copy.diet;
        health = copy.health;
        cuisineType = copy.cuisineType;
        mealType = copy.mealType;
        minCalories = copy.minCalories;
        maxCalories = copy.maxCalories;
        maxPreparationTime = copy.maxPreparationTime;
    }

    public GenerateRecipeState() {}

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getKeywordError() {return this.keywordError;}
    public void setKeywordError(String keywordError) {this.keywordError = keywordError;}


    public String getKeyword() {
        return keyword;
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

    public String getMinCalories() {
        return minCalories;
    }

    public String getMaxCalories() {
        return maxCalories;
    }

    public String getMaxPreparationTime() {
        return maxPreparationTime;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public void setDiet(String[] diet) {
        this.diet = diet;
    }

    public void setHealth(String[] health) {
        this.health = health;
    }

    public void setCuisineType(String[] cuisineType) {
        this.cuisineType = cuisineType;
    }

    public void setMealType(String[] mealType) {
        this.mealType = mealType;
    }

    public void setMinCalories(String minCalories) {
        this.minCalories = minCalories;
    }

    public void setMaxCalories(String maxCalories) {
        this.maxCalories = maxCalories;
    }

    public void setMaxPreparationTime(String maxPreparationTime) {
        this.maxPreparationTime = maxPreparationTime;
    }

    public String toString() {
        return "GenerateRecipeState{" +
                "keyword= " + keyword + "\n" +
                "diet= " + Arrays.toString(diet) + "\n" +
                "health= " + Arrays.toString(health) + "\n" +
                "cuisineType= " + Arrays.toString(cuisineType) + "\n" +
                "mealType= " + Arrays.toString(mealType) + "\n" +
                "minCalories= " + minCalories + "\n" +
                "maxCalories= " + maxCalories + "\n" +
                "maxPreparationTime= " + maxPreparationTime + "\n" +
                '}';
    }
}
