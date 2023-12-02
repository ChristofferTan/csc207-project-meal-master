package interface_adapters.generate_recipe;

import entity.Recipe;
import interface_adapters.ViewManagerModel;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeState;
import interface_adapters.after_generated_recipe.AfterGeneratedRecipeViewModel;
import use_case.generate_recipe.GenerateRecipeOutputBoundary;
import use_case.generate_recipe.GenerateRecipeOutputData;

public class GenerateRecipePresenter implements GenerateRecipeOutputBoundary {
    private final GenerateRecipeViewModel generateRecipeViewModel;
    private final AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel;
    private final ViewManagerModel viewManagerModel;

    public GenerateRecipePresenter(GenerateRecipeViewModel generateRecipeViewModel, AfterGeneratedRecipeViewModel afterGeneratedRecipeViewModel, ViewManagerModel viewManagerModel) {
        this.generateRecipeViewModel = generateRecipeViewModel;
        this.afterGeneratedRecipeViewModel = afterGeneratedRecipeViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(GenerateRecipeOutputData generateRecipeOutputData) {
        Recipe recipe = generateRecipeOutputData.getRecipe();

        AfterGeneratedRecipeState afterGeneratedRecipeState = afterGeneratedRecipeViewModel.getState();
        afterGeneratedRecipeState.setRecipe(recipe);
        afterGeneratedRecipeViewModel.setState(afterGeneratedRecipeState);
        afterGeneratedRecipeViewModel.firePropertyChanged();

        viewManagerModel.setActiveView(afterGeneratedRecipeViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
    public void prepareFailView(String error) {
        GenerateRecipeState state = generateRecipeViewModel.getState();
        state.setKeywordError(error);
        generateRecipeViewModel.firePropertyChanged();
    }
}
