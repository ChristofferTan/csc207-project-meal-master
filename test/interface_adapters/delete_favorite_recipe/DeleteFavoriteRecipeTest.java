package interface_adapters.delete_favorite_recipe;

import app.MyFavoriteRecipeFactory;
import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapters.my_favorite_recipe.MyFavoriteRecipeViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeDataAccessInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class DeleteFavoriteRecipeTest {
    private MyFavoriteRecipeViewModel myFavoriteRecipeViewModel;
    private DeleteFavoriteRecipeDataAccessInterface userDAO;
    private FileRecipeDataAccessObject recipeDAO;
    private DeleteFavoriteRecipeController controller;

    @BeforeEach
    void init() {
        myFavoriteRecipeViewModel = new MyFavoriteRecipeViewModel();
        try {
            userDAO = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        recipeDAO = new FileRecipeDataAccessObject(new RecipeFactory());
        controller = MyFavoriteRecipeFactory.createDeleteFavoriteRecipeUseCase(myFavoriteRecipeViewModel, userDAO, recipeDAO);
    }

    @Test
    public void testDeleteFavoriteRecipe() {
        // make the input data
        String label = "bebek goreng";
        String recipeUrl = "https://";
        String imagePath = "https://";
        int calories = 100;
        ArrayList<String> ingredients = new ArrayList<String>(Arrays.asList("donald bebek"));
        int preparationTime = 30;
        int yield = 20;

        String username = "andi";
        String password = "haihai";
        String repeatPassword = "haihai";
        String name = "Christoffer Tan";
        int age = 19;
        String gender = "Man";
        int height = 170;
        int weight = 67;

        Recipe recipe = new Recipe(label, recipeUrl, imagePath, calories, ingredients, preparationTime, yield);
        recipeDAO.save(recipe);

        User newUser = new CommonUserFactory().create(username, password, name, age, gender, height, weight, new Planner("andi"));
        userDAO.save(newUser);

        userDAO.saveFavoriteRecipe("andi", "bebek goreng");
        controller.execute("andi", "bebek goreng");

        Assertions.assertFalse(userDAO.isExists("andi", "bebek goreng"));
    }
}
