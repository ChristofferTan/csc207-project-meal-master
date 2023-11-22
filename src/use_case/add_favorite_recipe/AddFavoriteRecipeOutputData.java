package use_case.add_favorite_recipe;

import entity.Recipe;

public class AddFavoriteRecipeOutputData {
    private final String label;
    private final String url;

    public AddFavoriteRecipeOutputData(String label, String url) {
        this.label = label;
        this.url = url;
    }

    public String getLabel() {
        return label;
    }

    public String getUrl() {
        return url;
    }
}
