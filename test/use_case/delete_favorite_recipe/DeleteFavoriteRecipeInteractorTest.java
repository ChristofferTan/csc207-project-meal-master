package use_case.delete_favorite_recipe;

import data_access.FileRecipeDataAccessObject;
import data_access.FileUserDataAccessObject;
import entity.*;
import org.junit.Test;
import use_case.add_favorite_recipe.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class DeleteFavoriteRecipeInteractorTest {
    @Test
    public void SuccessTest() {
        UserFactory userFactory = new CommonUserFactory();
        RecipeFactory recipeFactory = new RecipeFactory();
        FileRecipeDataAccessObject recipeDataAccessObject;
        DeleteFavoriteRecipeDataAccessInterface userDataAccessObject;

        recipeDataAccessObject = new FileRecipeDataAccessObject(recipeFactory);
        try {
            userDataAccessObject = new FileUserDataAccessObject(userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user1 = userFactory.create("Janis", "joplin", "Jantod", 38, "Man", 90, 168);
        userDataAccessObject.save(user1);

        Recipe recipe = new Recipe("Asian-Style Chicken and Rice",
                "https://www.marthastewart.com/973886/asian-style-chicken-and-rice",
                "https://edamam-product-images.s3.amazonaws.com/web-img/735/7352082497426f599a8b37a9326ec803.jpg?X-Amz-Security-Token=IQoJb3JpZ2luX2VjEJf%2F%2F%2F%2F%2F%2F%2F%2F%2F%2FwEaCXVzLWVhc3QtMSJGMEQCIEvEOVhkYVEQsybGojXt4F952FlDEqgyE1GrrrMh1FK6AiB1vkWiXqQuuJAE8IZMrEOJHHh9rZgZnH2B910pIzCOSirBBQjg%2F%2F%2F%2F%2F%2F%2F%2F%2F%2F8BEAAaDDE4NzAxNzE1MDk4NiIMrILV0dcHsqFs1%2FXkKpUF2YVdJB28f4Zf%2B1B10899LNV01iGDUEw3udDT2Jk63XQA12lvwyCN3jhHXRYUgBsrn4D%2BcLz7Xduy8oRWfIMCLYcdOzg%2Ffni8%2F%2BvYMHcC4AXbaLsRkaUS8UcA0ICpac85OGg8ns53%2BUkoKn1UeV1cry1Q4572n8DfsMbB2%2F7VJnF%2Bugdz7HnOKoLq9Cmnbki5VV2%2Br0vgdx3Hrfj0AYwIjWl15Hqh0vClgHko8H4n5X%2BdwqgCstMDJGrTcNcl0dcwbP82DQ7ZRRpmupsEuhZ8fwM8cqUsCDRLU94VIR10WFhDww%2FHbuSceq2vr9nJzhyqUbEnxj8Fz3QlTNgvqdF1rNM%2Bax4z6C1tPHbpa07TeA2PqMZc0bb2HhA0pYUBV4vvvGZTI02fWnRmHNo7xOOrB5IeWEZulQWHSFZXos3ID15T6dGwmY5hvDmJdWt7WqFLoiM%2FXNZk%2BAejT7z65iEUPGW4ZM0oXzq%2B9KbFWtsiMaA%2BFKspRXTY9KI3DaL0Epy2kIZmk7X%2B69AhdiCVhvZ%2Fx6G05FGMMY0dFy3Nc9tiMP0rTZyZDNrj6Ml2FoHNUt4RGjqjRxiI9tQ3k7LtDQnK5Gol83OrL18JZ%2BvCvMk7pnWQTTxlFgCOL2admpeUZq5EuPhLE5pkNpMVNKHu5Tv%2BIjwAzyvdu3TbhCjDmRjLYex4i9j98fufQwN2G1TgaiWaz5qJFfetXrqH3hx9h6t4GrS51wAMV9F%2FWwxgwytv4OFAp1dndNi8oLOkVLW5xms097b8Ry3CdUyI%2FMyl3CNcECqwKwNg7cNch4Jx%2FKpNJAsyjRtSxtUNSk31EaubZx6Pe4TFTqUxfH%2BC1PXwGy88NUz9G744CFJFH61ex6oKmHXQQHbN8DCPo%2BqqBjqyAYJiAMpLhcyOVCGwXHWIgswwmaavxRmlHqsE9ITdyHCFscgSaz1yO5mKRnhh5dHZxL72QZXUzrfTxoYoRTL1mD%2F9TXwnZyitm%2BFPL%2FPUBLdj05%2BHgbkqFCZo5n9Kcr2hYQfQh7sifc0wrDpkJy2YFig%2FJTR4iqJI4Z02oXcvKovJzJiXZZUD7KBF%2FijLTqAHUowC0z%2FgF5Cgn6%2Ff1V9VfeeDNo0N3svgR%2FFN4Eq99ln8bjE%3D&X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20231119T232629Z&X-Amz-SignedHeaders=host&X-Amz-Expires=3600&X-Amz-Credential=ASIASXCYXIIFIDBYH6EZ%2F20231119%2Fus-east-1%2Fs3%2Faws4_request&X-Amz-Signature=f76f87ef51e9a879b462b85b97b3b6e18ef37866647964292b5ee17ae540278c",
                2403,
                new ArrayList<String>(Arrays.asList("2 tablespoons olive oil","4 bone-in", "skin-on chicken thighs (about 1 pound)","Salt and pepper","4 scallions", "white and green parts separated and thinly sliced","2 tablespoons finely chopped peeled fresh ginger","3 garlic cloves", "minced","2 ounces shiitake mushroom caps (about 3/4 cups)", "thinly sliced","1 1/2 cups Arborio rice","3 2/3 cups chicken broth,Fresh cilantro", "for serving")),
                50,10);
        recipeDataAccessObject.save(recipe);
        userDataAccessObject.saveFavoriteRecipe(user1.getUsername(), recipe.getLabel());

        DeleteFavoriteRecipeInputData deleteFavoriteRecipeInputData = new DeleteFavoriteRecipeInputData("Janis", recipe.getLabel());
        DeleteFavoriteRecipeOutputBoundary successPresenter = new DeleteFavoriteRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteFavoriteRecipeOutputData deleteFavoriteRecipeOutputData) {
                assertFalse(userDataAccessObject.isExists("Janis", recipe.getLabel()));
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected.");
            }
        };
        DeleteFavoriteRecipeInputBoundary interactor = new DeleteFavoriteRecipeInteractor(userDataAccessObject, recipeDataAccessObject, successPresenter);
        interactor.execute(deleteFavoriteRecipeInputData);
    }
    @Test
    public void FailureRecipeIsNotInFavoriteRecipe() {
        UserFactory userFactory = new CommonUserFactory();
        RecipeFactory recipeFactory = new RecipeFactory();
        FileRecipeDataAccessObject recipeDataAccessObject;
        DeleteFavoriteRecipeDataAccessInterface userDataAccessObject;

        recipeDataAccessObject = new FileRecipeDataAccessObject(recipeFactory);
        try {
            userDataAccessObject = new FileUserDataAccessObject(userFactory);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user1 = userFactory.create("Janis", "joplin", "Jantod", 38, "Man", 90, 168);
        userDataAccessObject.save(user1);

        Recipe recipe = new Recipe("Indomie Goreng","github.com/ChristofferTan/csc207-project","github.com/elidle",350,new ArrayList<String>(Arrays.asList("1 bungkus Indomie Goreng")),5,1);
        recipeDataAccessObject.save(recipe);

        DeleteFavoriteRecipeInputData deleteFavoriteRecipeInputData = new DeleteFavoriteRecipeInputData("Janis", "indomie");
        DeleteFavoriteRecipeOutputBoundary successPresenter = new DeleteFavoriteRecipeOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteFavoriteRecipeOutputData deleteFavoriteRecipeOutputData) {
                fail("Use case success is unexpected.");
            }

            @Override
            public void prepareFailView(String error) {
                assertEquals("You do not have this recipe in My Favorite Recipe", error);
            }
        };
        DeleteFavoriteRecipeInputBoundary interactor = new DeleteFavoriteRecipeInteractor(userDataAccessObject, recipeDataAccessObject, successPresenter);
        interactor.execute(deleteFavoriteRecipeInputData);
    }
}
