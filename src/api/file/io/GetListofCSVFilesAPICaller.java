package api.file.io;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class GetListofCSVFilesAPICaller {
    public static void main(String[] args) {
        HashMap<String, String> res = call();
        for (String key : res.keySet()) {
            System.out.println(key + " : " + res.get(key));
        }
    }

    private GetListofCSVFilesAPICaller() {};  // prevent instantiation

    /**
     * Call the API to get the list of CSV files
     * @return a HashMap of the CSV files in the format of <filename, link>
     */
    public static HashMap<String, String> call() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(UploadCSVFilesAPICaller.API_URL)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", UploadCSVFilesAPICaller.API_AUTHORIZATION)
                .build();
        try {
            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                JSONObject responseBody = new JSONObject(response.body().string());
                int filesCount = responseBody.getInt("count");
                System.out.println("There are " + filesCount + " files in the server");
                JSONArray filesInJSONArray = responseBody.getJSONArray("nodes");
                HashMap<String, String> files = new HashMap<>();
                for (int i=0; i<filesCount; i++) {
                    JSONObject currentFile = filesInJSONArray.getJSONObject(i);
                    files.put(currentFile.getString("name"), currentFile.getString("link"));
                }
                return files;
            } else {
                throw new RuntimeException(response.message());
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
