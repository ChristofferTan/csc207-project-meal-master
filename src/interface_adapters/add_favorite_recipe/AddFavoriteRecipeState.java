package interface_adapters.add_favorite_recipe;

public class AddFavoriteRecipeState {
    private final String username;
    private final String label;

    public AddFavoriteRecipeState(AddFavoriteRecipeState copy) {
        username = copy.username;
        label = copy.label;
    }

    public String getUsername() {
        return username;
    }

    public String getLabel() {
        return label;
    }
}
