package entity;

public class Ingredient {
    private final String text;
    private final String food;
    private final double quantity;
    private final String measure;
    private final double weight;

    public Ingredient(String text, String food, double quantity, String measure, double weight) {
        this.text = text;
        this.food = food;
        this.quantity = quantity;
        this.measure = measure;
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

    public String getMeasure() {
        return measure;
    }

    public double getWeight() {
        return weight;
    }
}
