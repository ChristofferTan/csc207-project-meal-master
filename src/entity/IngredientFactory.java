package entity;

public class IngredientFactory {
    public static Ingredient create(String text, String food, double quantity, double weight) {
        return new Ingredient(text, food, quantity, weight);
    }
}
