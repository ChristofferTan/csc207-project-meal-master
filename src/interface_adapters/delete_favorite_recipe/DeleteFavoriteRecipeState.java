package interface_adapters.delete_favorite_recipe;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
