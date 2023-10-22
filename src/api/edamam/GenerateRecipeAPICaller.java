package api.edamam;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import use_case.GenerateRecipeInputData;

import java.io.IOException;

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
                String[] ingredientLines = new String[ingredientLinesInJSON.length()];
                for (int i=0; i<ingredientLinesInJSON.length(); i++) {
                    ingredientLines[i] = ingredientLinesInJSON.get(i).toString();
                }
                return new GenerateRecipeAPIData(
                        recipe.getString("label"),
                        recipe.getInt("calories"),
                        ingredientLines,
                        recipe.getString("url"));
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
