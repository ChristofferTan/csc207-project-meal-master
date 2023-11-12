package view;

// TODO: implement test if possible

//
//import api.edamam.GenerateRecipeAPICaller;
//import app.SaveRecipeFactory;
//import data_access.InMemoryPlannerDataAccessObject;
//import entity.MealType;
//import entity.Recipe;
//import interface_adapters.ViewManagerModel;
//import interface_adapters.save_recipe.SaveRecipeController;
//import interface_adapters.save_recipe.SaveRecipeViewModel;
//import use_case.generate_recipe.GenerateRecipeInputData;
//
//import java.time.DayOfWeek;
//
//public class SaveRecipeTest {
//    @org.junit.Test
//    public void testSaveRecipe() {
//        InMemoryPlannerDataAccessObject inMemoryPlannerDataAccessObject = new InMemoryPlannerDataAccessObject();
//        SaveRecipeView saveRecipeView = SaveRecipeFactory.create(
//                new ViewManagerModel(),
//                new SaveRecipeViewModel(),
//                inMemoryPlannerDataAccessObject
//        );
//        SaveRecipeController saveRecipeController = saveRecipeView.getSaveRecipeController();
//        GenerateRecipeInputData generateRecipeInputData = new GenerateRecipeInputData(
//                "chicken",
//                new String[]{"balanced"},
//                new String[]{"alcohol-free"},
//                new String[]{"Asian"},
//                new String[]{"Lunch"},
//                "100-1000",
//                "0-100"
//        );
//        Recipe expectedRecipe = GenerateRecipeAPICaller.call(generateRecipeInputData).getRecipe();
//        saveRecipeController.execute(expectedRecipe, DayOfWeek.MONDAY, MealType.LUNCH);
//        Recipe actualRecipe = inMemoryPlannerDataAccessObject.get("budi", DayOfWeek.MONDAY, MealType.LUNCH);
//        assert expectedRecipe == actualRecipe;
//    }
//}
