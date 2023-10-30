package use_case.generate_recipe;

import api.edamam.GenerateRecipeAPICaller;

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
        GenerateRecipeOutputData generateRecipeOutputData = new GenerateRecipeOutputData(
                GenerateRecipeAPICaller.call(generateRecipeInputData)
        );
        generateRecipePresenter.prepareSuccessView(generateRecipeOutputData);
    }
}
