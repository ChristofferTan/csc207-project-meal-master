package view;

import api.edamam.GenerateRecipeAPICaller;
import app.SaveRecipeUseCaseFactory;
import data_access.FilePlannerDataAccessObject;
import data_access.FileRecipeDataAccessObject;
import entity.MealType;
import entity.PlannerFactory;
import entity.Recipe;
import entity.RecipeFactory;
import interface_adapters.ViewManagerModel;
import interface_adapters.save_recipe.SaveRecipeController;
import interface_adapters.save_recipe.SaveRecipeViewModel;
import use_case.generate_recipe.GenerateRecipeInputData;

import java.time.DayOfWeek;

public class SaveRecipeTest {
    @org.junit.Test
    public void testSaveRecipe() {
        FilePlannerDataAccessObject fpdao = new FilePlannerDataAccessObject(new PlannerFactory(), new FileRecipeDataAccessObject(new RecipeFactory()));
        SaveRecipeView saveRecipeView = SaveRecipeUseCaseFactory.create(
                new ViewManagerModel(),
                new SaveRecipeViewModel(),
                fpdao
        );
        SaveRecipeController saveRecipeController = saveRecipeView.getSaveRecipeController();
        GenerateRecipeInputData generateRecipeInputData = new GenerateRecipeInputData(
                "chicken",
                new String[]{"balanced"},
                new String[]{"alcohol-free"},
                new String[]{"Asian"},
                new String[]{"Lunch"},
                "100-1000",
                "0-100"
        );
        Recipe expectedRecipe = GenerateRecipeAPICaller.call(generateRecipeInputData).getRecipe();
        saveRecipeController.execute("budi", DayOfWeek.MONDAY, MealType.LUNCH, expectedRecipe);
        Recipe actualRecipe = fpdao.getPlanner("budi").getRecipesByDay(DayOfWeek.MONDAY).get(MealType.LUNCH);
        assert expectedRecipe == actualRecipe;
    }
}
