package entity;

import java.util.ArrayList;

public class Grocery {
    private ArrayList<Ingredient> ingredients;

    Grocery(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }
}
