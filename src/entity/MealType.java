package entity;

public enum MealType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner");

    private final String stringValue;

    private MealType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String toString() {
        return stringValue;
    }

    public static MealType fromString(String stringValue) {
        for (MealType mealType : MealType.values()) {
            if (mealType.name().equals(stringValue)) {
                return mealType;
            }
        }
        return null;
    }
}
