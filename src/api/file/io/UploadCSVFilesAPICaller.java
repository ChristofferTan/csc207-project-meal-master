package api.file.io;

import okhttp3.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;

public class UploadCSVFilesAPICaller {
    protected static final String API_URL = "https://file.io/";
    private static final String API_KEY = "GISGFAZ.PP3BR31-2Q5MXX7-M6ZJHEJ-78C4YHT";
    protected static final String API_AUTHORIZATION = "Bearer " + API_KEY;
    private static final String API_EXPIRATION_DATE = "2024-11-10";


    private UploadCSVFilesAPICaller() {}    // prevent instantiation

    /**
     * Call the API to upload the CSV file at <filePath> and return the link to the file
     * @param filePath the path to the file (starts from the root of the project csc207-project)
     * @return the link to the file
     */
    public static String call(String filePath) {
        OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Create a request body with the file
        File csvFile = new File(filePath);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("file", csvFile.getName(), RequestBody.create(MediaType.parse("text/csv"), csvFile))
                .addFormDataPart("expires", API_EXPIRATION_DATE)
                .build();

        // Build the request
        Request request = new Request.Builder()
                .url(API_URL)
                .post(requestBody)
                .addHeader("accept", "application/json")
                .addHeader("Authorization", API_AUTHORIZATION)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (!response.isSuccessful()) {
                throw new RuntimeException("Failed with HTTP error code: " + response.code());
            }

            // Parse the response JSON
            JSONObject responseBody = new JSONObject(response.body().string());
            return responseBody.getString("link");
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
