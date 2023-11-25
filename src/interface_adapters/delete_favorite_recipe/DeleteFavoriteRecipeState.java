package interface_adapters.delete_favorite_recipe;

import interface_adapters.add_favorite_recipe.AddFavoriteRecipeState;

public class DeleteFavoriteRecipeState {
    private String username;
    private String label;

    public DeleteFavoriteRecipeState(DeleteFavoriteRecipeState copy) {
        username = copy.username;
        label = copy.label;
    }

    public DeleteFavoriteRecipeState() {

    }

    public String getUsername() {
        return username;
    }

    public String getLabel() {
        return label;
    }
}
