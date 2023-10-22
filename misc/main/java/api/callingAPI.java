package api;

import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class callingAPI {
    // Constants
    private static final String API_URL = "https://api.edamam.com/api/recipes/v2";
    private static final String APP_ID = "97884852";
    private static final String APP_KEY = "29ec3f53238d1c6ec3c16c6412bc91ea";

    public static void main(String[] args) throws JSONException {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        String type = "public";
        String q = "chicken";
        MediaType mediaType = MediaType.parse("application/json");
        String requestUrl = API_URL + "?type=" + type + "&q=" + q + "&app_id=" + APP_ID + "&app_key=" + APP_KEY;
        Request request = new Request.Builder()
                .url(requestUrl)
                .method("GET", null)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            JSONObject responseBody = new JSONObject(response.body().string());
            if (response.code() == 200) {
                System.out.println("The call succeed! Some information about the first recipe is shown below.");
                JSONObject recipe = responseBody
                        .getJSONArray("hits")
                            .getJSONObject(0)
                                .getJSONObject("recipe");
                System.out.println(recipe.getString("label"));
                System.out.println("Calories: " + recipe.getFloat("calories"));
                System.out.println("\nIngredients:");
                JSONArray ingredients = recipe.getJSONArray("ingredientLines");
                for (Object ingredient : ingredients) {
                    System.out.println(ingredient);
                }
                System.out.println("\nInstructions at " + recipe.getString("url"));
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
