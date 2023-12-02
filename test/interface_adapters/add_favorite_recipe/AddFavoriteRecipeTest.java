package interface_adapters.add_favorite_recipe;

import app.AfterGeneratedRecipeFactory;
import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapters.ViewManagerModel;
import interface_adapters.add_favorite_recipe.AddFavoriteRecipeController;
import interface_adapters.add_favorite_recipe.AddFavoriteRecipeViewModel;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import use_case.add_favorite_recipe.AddFavoriteRecipeUserDataAccessInterface;
import use_case.generate_recipe.GenerateRecipeDataAccessInterface;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddFavoriteRecipeTest {
    private ViewManagerModel viewManagerModel;
    private AddFavoriteRecipeViewModel addFavoriteRecipeViewModel;
    private AddFavoriteRecipeUserDataAccessInterface userDAO;
    private GenerateRecipeDataAccessInterface recipeDAO;
    private AddFavoriteRecipeController controller;

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        addFavoriteRecipeViewModel = new AddFavoriteRecipeViewModel();
        try {
            userDAO = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        recipeDAO = new FileRecipeDataAccessObject(new RecipeFactory());
        controller = AfterGeneratedRecipeFactory.createAddFavoriteUseCase(viewManagerModel, addFavoriteRecipeViewModel, userDAO, recipeDAO);
    }
    @Test
    public void testAddFavoriteRecipe() {
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

        controller.execute("andi", "bebek goreng");

        Assertions.assertTrue(userDAO.isExists("andi", "bebek goreng"));
    }

}
