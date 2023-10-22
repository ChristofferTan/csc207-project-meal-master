package use_case;

public class GenerateRecipeInteractor implements GenerateRecipeInputBoundary {
    final GenerateRecipeDataAccessInterface dataAccessInterface;
    final GenerateRecipeOutputBoundary generateRecipePresenter;

    public GenerateRecipeInteractor(GenerateRecipeDataAccessInterface dataAccessInterface,
                                    GenerateRecipeOutputBoundary generateRecipeOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.generateRecipePresenter = generateRecipeOutputBoundary;
    }

    @Override
    public void execute(GenerateRecipeInputData generateRecipeInputData) {

    }
}
