package api.file.io;

import data_access.OnlineFileStorageManager;

import java.util.HashMap;

public class FileIOFacade implements OnlineFileStorageManager {
    public String download(String link) {
        return DownloadCSVFilesAPICaller.call(link);
    }

    public HashMap<String, String> getFileList() {
        return GetListofCSVFilesAPICaller.call();
    }

    public String upload(String filepath) {
        return UploadCSVFilesAPICaller.call(filepath);
    }
}
