package entity;

import java.util.ArrayList;

public class Grocery {
    private ArrayList<String> ingredients;

    Grocery(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }

    public ArrayList<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(ArrayList<String> ingredients) {
        this.ingredients = ingredients;
    }
}
