package use_case.my_favorite_recipes;

public class MyFavoriteRecipeInputData {
    final private String username;

    public MyFavoriteRecipeInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
