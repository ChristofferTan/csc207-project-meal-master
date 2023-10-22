package entity;

import java.util.List;

public class Grocery {
    public final List<Ingredient> ingredients;

    Grocery(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }
}
