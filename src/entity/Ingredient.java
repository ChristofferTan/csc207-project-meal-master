package entity;

public class Ingredient {
    private final String text;
    private final String food;
    private final double quantity;
    private final double weight;

    public Ingredient(String text, String food, double quantity, double weight) {
        this.text = text;
        this.food = food;
        this.quantity = quantity;
        this.weight = weight;
    }

    public String getText() {
        return text;
    }

    public String getFood() {
        return food;
    }

    public double getQuantity() {
        return quantity;
    }

    public double getWeight() {
        return weight;
    }
}
