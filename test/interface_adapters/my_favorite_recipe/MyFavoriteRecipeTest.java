package interface_adapters.my_favorite_recipe;

import app.MyFavoriteRecipeFactory;
import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import interface_adapters.ViewManagerModel;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyFavoriteRecipeTest {
    private ViewManagerModel viewManagerModel;
    private MyFavoriteRecipeViewModel myFavoriteRecipeViewModel;
    private FileUserDataAccessObject userDAO;
    private FileRecipeDataAccessObject recipeDAO;
    private MyFavoriteRecipeController controller;
    private String username = "RazanAr4";

    @BeforeEach
    void init() {
        viewManagerModel = new ViewManagerModel();
        myFavoriteRecipeViewModel = new MyFavoriteRecipeViewModel();

        RecipeFactory recipeFactory = new RecipeFactory();
        recipeDAO = new FileRecipeDataAccessObject(recipeFactory);

        Recipe recipe1 = recipeFactory.create("Indomie Goreng", "https://github.com/ChristofferTan/csc207-project",
                null, 350, new ArrayList<String>(Arrays.asList("1 bungkus Indomie Goreng")), 5, 1);
        recipeDAO.save(recipe1);

        Recipe recipe2 = recipeFactory.create("Indomie Kuah", "https://github.com/ChristofferTan/csc207-project",
                null, 350, new ArrayList<String>(Arrays.asList("1 bungkus Indomie Kuah")), 5, 1);
        recipeDAO.save(recipe2);

        try {
            userDAO = new FileUserDataAccessObject(new CommonUserFactory());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        User newUser = new CommonUserFactory().create(username, "admin", "Razan", 17, "Man", 177, 70, new Planner(username));
        userDAO.save(newUser);
        userDAO.saveFavoriteRecipe(username, recipe1.getLabel());
        userDAO.saveFavoriteRecipe(username, recipe2.getLabel());

        controller = MyFavoriteRecipeFactory.createMyFavoriteRecipeUseCase(viewManagerModel, myFavoriteRecipeViewModel, userDAO);
    }
    @Test
    public void testMyFavoriteRecipe() {
        controller.execute(username);

        // Check that the fetched data is correct
        User user = userDAO.get(username);

        Assertions.assertEquals(user.getFavoriteRecipes(), new ArrayList<>(List.of("Indomie Goreng", "Indomie Kuah")));
    }
}
