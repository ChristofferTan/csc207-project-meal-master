package api.edamam;

import entity.RecipeFactory;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.generate_recipe.GenerateRecipeInputData;

import java.io.IOException;
import java.util.ArrayList;

public class GenerateRecipeAPICaller {
    private GenerateRecipeAPICaller() {}

    private static final String API_URL = "https://api.edamam.com/api/recipes/v2";
    private static final String APP_ID = "97884852";
    private static final String APP_KEY = "29ec3f53238d1c6ec3c16c6412bc91ea";


    /**
     * Used in GenerateRecipeInteractor, to generate a recipe based on the user's input
     * @param inputData
     * @return GenerateRecipeAPIData with a generated recipe inside it
     */
    public static GenerateRecipeAPIData call(GenerateRecipeInputData inputData) {
        final RecipeFactory recipeFactory = new RecipeFactory();

        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String type = "public";
        MediaType mediaType = MediaType.parse("application/json");

        StringBuilder requestUrl = new StringBuilder(API_URL + "?type=" + type + "&app_id=" + APP_ID + "&app_key=" + APP_KEY);
        requestUrl.append("&q=").append(inputData.getQ());
        for (String item : inputData.getDiet()) {
            if (item != null) {
                requestUrl.append("&diet=").append(item);
            }
        }
        for (String item : inputData.getHealth()) {
            if (item != null) {
                requestUrl.append("&health=").append(item);
            }
        }
        for (String item : inputData.getCuisineType()) {
            if (item != null) {
                requestUrl.append("&cuisineType=").append(item);
            }
        }
        for (String item : inputData.getMealType()) {
            if (item != null) {
                requestUrl.append("&mealType=").append(item);
            }
        }
        requestUrl.append("&calories=").append(inputData.getCalories());
        requestUrl.append("&time=").append(inputData.getPreparationTime());
        System.out.println(requestUrl);
        Request request = new Request.Builder()
                .url(requestUrl.toString())
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                JSONObject recipe = responseBody
                        .getJSONArray("hits")
                        .getJSONObject(0)
                        .getJSONObject("recipe");
                JSONArray ingredientLinesInJSON = recipe.getJSONArray("ingredientLines");
                ArrayList<String> ingredients = new ArrayList<>();
                for (int i=0; i<ingredientLinesInJSON.length(); i++) {
                    ingredients.add(ingredientLinesInJSON.getString(i));
                }

                return new GenerateRecipeAPIData(recipeFactory.create(
                        recipe.getString("label"),
                        recipe.getString("url"),
                        recipe.getString("image"),
                        recipe.getInt("calories"),
                        ingredients,
                        recipe.getInt("totalTime"),
                        recipe.getInt("yield"))
                );
            } else {
                return null;
                // throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            return null;
           // throw new RuntimeException(e);
        }
    }
}
