package entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MealTypeTest {
    private MealType breakfast;
    private MealType lunch;
    private MealType dinner;

    @BeforeEach
    void init() {
        breakfast = MealType.BREAKFAST;
        lunch = MealType.LUNCH;
        dinner = MealType.DINNER;
    }

    @Test
    void toStringTest() {
        assertEquals("Breakfast", breakfast.toString());
        assertEquals("Lunch", lunch.toString());
        assertEquals("Dinner", dinner.toString());
    }

    @Test
    void fromStringTest() {
        assertEquals(MealType.BREAKFAST, MealType.fromString("Breakfast"));
        assertEquals(MealType.LUNCH, MealType.fromString("Lunch"));
        assertEquals(MealType.DINNER, MealType.fromString("Dinner"));
    }

    @Test
    void fromStringNullTest() {
        assertEquals(null, MealType.fromString("Breakfastt"));
        assertEquals(null, MealType.fromString("Luncch"));
        assertEquals(null, MealType.fromString("Dinneer"));
    }
}
