package api.file.io;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;

import java.io.IOException;

public class DownloadCSVFilesAPICaller {
    private DownloadCSVFilesAPICaller() {};  // prevent instantiation

    /**
     * Call the API to download the CSV file and convert it to a string
     * @param link the link to the file
     * @return a string of the file content, each line separated by \n
     */
    public static String call(String link) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        Request request = new Request.Builder()
                .url(link)
                .addHeader("accept", "*/*")
                .addHeader("Authorization", UploadCSVFilesAPICaller.API_AUTHORIZATION)
                .build();
        try {
            Response response = client.newCall(request).execute();

            if (response.code() == 200) {
                assert response.body() != null;
                return response.body().string();
            } else {
                throw new RuntimeException(response.message() + " in " + link);
            }
        } catch (IOException | JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
