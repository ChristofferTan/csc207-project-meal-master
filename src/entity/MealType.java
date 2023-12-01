package entity;

public enum MealType {
    BREAKFAST("Breakfast"),
    LUNCH("Lunch"),
    DINNER("Dinner"),
    SNACK("Snack"),
    TEATIME("Teatime");

    private final String stringValue;

    private MealType(String stringValue) {
        this.stringValue = stringValue;
    }

    public String toString() {
        return stringValue;
    }

    public static MealType fromString(String stringValue) {
        for (MealType mealType : MealType.values()) {
            if (mealType.stringValue.equals(stringValue)) {
                return mealType;
            }
        }
        return null;
    }
}
