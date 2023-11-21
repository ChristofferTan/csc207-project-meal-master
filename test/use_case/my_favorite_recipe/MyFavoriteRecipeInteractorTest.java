package use_case.my_favorite_recipe;

import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.jupiter.api.Test;
import use_case.my_favorite_recipes.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MyFavoriteRecipeInteractorTest {
    @Test
    void successTest() {
        UserFactory userFactory = new CommonUserFactory();
        RecipeFactory recipeFactory = new RecipeFactory();
        FileRecipeDataAccessObject recipeDAO;
        MyFavoriteRecipeDataAccessInterface userDAO;

        try {
            recipeDAO = new FileRecipeDataAccessObject(recipeFactory);
            userDAO = new FileUserDataAccessObject(userFactory, recipeDAO);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User user = userFactory.create("RazanAr", "admin", "Razan", 17, "Male", 70, 177);
        ((FileUserDataAccessObject) userDAO).save(user);
        Recipe recipe = recipeFactory.create("Indomie Goreng", "github.com/ChristofferTan/csc207-project",
                "github.com/elidle", 350, new ArrayList<String>(Arrays.asList("1 bungkus Indomie Goreng")),
                5, 1);
        recipeDAO.save(recipe);
        ((FileUserDataAccessObject) userDAO).saveFavoriteRecipe("RazanAr", recipe);

        MyFavoriteRecipeInputData inputData = new MyFavoriteRecipeInputData("RazanAr");
        MyFavoriteRecipeOutputBoundary successPresenter = new MyFavoriteRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(MyFavoriteRecipeOutputData myFavoriteRecipeOutputData) {
                assertTrue(((FileUserDataAccessObject) userDAO).isExists("RazanAr", recipe));
                ArrayList<Recipe> favoriteRecipes = myFavoriteRecipeOutputData.getFavoriteRecipes();
                assertEquals(new ArrayList<Recipe>(Arrays.asList(recipe)), favoriteRecipes);
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        MyFavoriteRecipeInputBoundary interactor = new MyFavoriteRecipeInteractor(userDAO, successPresenter);
        interactor.execute(inputData);
    }
}
