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
    private static final String APP_KEY = "29ec3f53238d1c6ec3c16c6412bc91ea    —";
    public static void main(String[] args) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();
        MediaType mediaType = MediaType.parse("application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("q", "chicken");
        requestBody.put("app_id", APP_ID);
        requestBody.put("app_key", APP_KEY);
        RequestBody body = RequestBody.create(mediaType, requestBody.toString());
        Request request = new Request.Builder()
                .url(API_URL)
                .method("POST", body)
//                .addHeader("Authorization", API_TOKEN)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            Response response = client.newCall(request).execute();
            System.out.println(response);
            JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt("status_code") == 200) {
                System.out.println("The call succeed!");
//                JSONObject team = responseBody.getJSONObject("team");
//                JSONArray membersArray = team.getJSONArray("members");
//                String[] members = new String[membersArray.length()];
//                for (int i = 0; i < membersArray.length(); i++) {
//                    members[i] = membersArray.getString(i);
//                }
//
//                return Team.builder()
//                        .name(team.getString("name"))
//                        .members(members)
//                        .build();
            } else {
                throw new RuntimeException(responseBody.getString("message"));
            }
        }
        catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }

}
