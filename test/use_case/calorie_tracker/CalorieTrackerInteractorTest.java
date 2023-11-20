package use_case.calorie_tracker;

import entity.MealType;
import entity.Planner;
import entity.Recipe;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class CalorieTrackerInteractorTest {
    @Test
    void successTest() {
        //Making a planner with 2 recipes for 2 days and checking if the calories are correct
        Planner planner = new Planner("faraaz");
        ArrayList<String> ingredients = new ArrayList<>();
        Recipe recipe = new Recipe("food", "url", "url", 100, ingredients, 1, 1);
        HashMap<MealType, Recipe> meal = new HashMap<>();
        meal.put(MealType.BREAKFAST, recipe);
        planner.getWeeklyRecipes().put(DayOfWeek.MONDAY, meal);
        planner.getWeeklyRecipes().put(DayOfWeek.TUESDAY, meal);

        CalorieTrackerInputData inputData = new CalorieTrackerInputData(planner);

        CalorieTrackerOutputBoundary successPresenter = new CalorieTrackerOutputBoundary() {
            @Override
            public void prepareSuccessView(CalorieTrackerOutputData calories) {
                //checking weekly calories
                Assertions.assertEquals(200, calories.getWeeklyCalories());
                //checking average daily calories
                Assertions.assertEquals(29, calories.getAverageDailyCalories());
            }
        };

        CalorieTrackerInputBoundary interactor = new CalorieTrackerInteractor(null, successPresenter);
        interactor.execute(inputData);

    }
}
