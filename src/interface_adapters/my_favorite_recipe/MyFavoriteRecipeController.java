package interface_adapters.my_favorite_recipe;

import use_case.my_favorite_recipes.MyFavoriteRecipeInputBoundary;
import use_case.my_favorite_recipes.MyFavoriteRecipeInputData;

public class MyFavoriteRecipeController {
    final MyFavoriteRecipeInputBoundary myFavoriteRecipeInteractor;

    public MyFavoriteRecipeController(MyFavoriteRecipeInputBoundary myFavoriteRecipeInteractor) {
        this.myFavoriteRecipeInteractor = myFavoriteRecipeInteractor;
    }

    public void execute(String username) {
        MyFavoriteRecipeInputData myFavoriteRecipeInputData = new MyFavoriteRecipeInputData(username);
        myFavoriteRecipeInteractor.execute(myFavoriteRecipeInputData);
    }
}
