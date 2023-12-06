package use_case.generate_recipe;

import api.edamam.GenerateRecipeAPIData;

public interface GenerateRecipeAPICallerInterface {
    GenerateRecipeAPIData call(GenerateRecipeInputData inputData);
}
