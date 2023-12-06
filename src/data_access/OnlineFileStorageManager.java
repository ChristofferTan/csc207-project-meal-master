package data_access;

import java.util.HashMap;

public interface OnlineFileStorageManager {
    String download(String link);
    HashMap<String, String> getFileList();
    String upload(String filepath);
}
