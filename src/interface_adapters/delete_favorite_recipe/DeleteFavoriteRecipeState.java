package interface_adapters.delete_favorite_recipe;

import interface_adapters.add_favorite_recipe.AddFavoriteRecipeState;

public class DeleteFavoriteRecipeState {
    private final String username;
    private final String label;

    public DeleteFavoriteRecipeState(DeleteFavoriteRecipeState copy) {
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
