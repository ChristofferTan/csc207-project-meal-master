package interface_adapters.delete_favorite_recipe;

public class DeleteFavoriteRecipeState {
    private String username;
    private String label;
    private String labelError;

    public DeleteFavoriteRecipeState(DeleteFavoriteRecipeState copy) {
        username = copy.username;
        label = copy.label;
        labelError = copy.labelError;
    }

    public DeleteFavoriteRecipeState() {

    }

    public String getUsername() {
        return username;
    }

    public String getLabel() {
        return label;
    }

    public String getLabelError() {
        return labelError;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public void setLabelError(String labelError) {
        this.labelError = labelError;
    }
}
