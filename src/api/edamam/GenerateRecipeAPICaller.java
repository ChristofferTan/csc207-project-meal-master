package api.edamam;

import entity.Ingredient;
import entity.IngredientFactory;
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

    public static GenerateRecipeAPIData call(GenerateRecipeInputData inputData) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String type = "public";
        MediaType mediaType = MediaType.parse("application/json");

        StringBuilder requestUrl = new StringBuilder(API_URL + "?type=" + type + "&app_id=" + APP_ID + "&app_key=" + APP_KEY);
        requestUrl.append("&q=").append(inputData.getQ());
        for (String item : inputData.getDiet()) {
            requestUrl.append("&diet=").append(item);
        }
        for (String item : inputData.getHealth()) {
            requestUrl.append("&health=").append(item);
        }
        for (String item : inputData.getCuisineType()) {
            requestUrl.append("&cuisineType=").append(item);
        }
        for (String item : inputData.getMealType()) {
            requestUrl.append("&mealType=").append(item);
        }
        requestUrl.append("&calories=").append(inputData.getCalories());
        requestUrl.append("&time=").append(inputData.getPreparationTime());

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
                JSONArray ingredientsInJSON = recipe.getJSONArray("ingredients");
                ArrayList<Ingredient> ingredients = new ArrayList<>();
                for (int i=0; i<ingredientsInJSON.length(); i++) {
                    JSONObject currentIngredient = ingredientsInJSON.getJSONObject(i);
                    ingredients.add(IngredientFactory.create(currentIngredient.getString("text"),
                            currentIngredient.getString("food"),
                            currentIngredient.getDouble("quantity"),
                            currentIngredient.getString("measure"),
                            currentIngredient.getDouble("weight")));
                }

                return new GenerateRecipeAPIData(RecipeFactory.create(
                        recipe.getString("label"),
                        recipe.getString("url"),
                        recipe.getString("image"),
                        recipe.getInt("calories"),
                        ingredients,
                        recipe.getInt("totalTime"),
                        recipe.getInt("yield"))
                );
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
