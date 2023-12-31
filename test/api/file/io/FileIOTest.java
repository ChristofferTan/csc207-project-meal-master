package api.file.io;

import data_access.OnlineFileStorageManager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class FileIOTest {
    private final OnlineFileStorageManager onlineFileStorageManager = new FileIOFacade();
    private void writeToCSV(String content, String path) {
        BufferedWriter writer;
        try {
            File csvFile = new File(path);
            writer = new BufferedWriter(new FileWriter(csvFile));

            writer.write(content);

            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @org.junit.Test
    public void testUploadGetDownload() {
        final String fileName = "test.csv";
        final String filePath = "test/api/file/io/" + fileName;
        String expectedCSVContent = "tast,1\ntest,2\ntist,3";
        writeToCSV(expectedCSVContent, filePath);
        String expectedLink = onlineFileStorageManager.upload(filePath);
        HashMap<String,String> actualListOfCSVFiles = onlineFileStorageManager.getFileList();
        // check if the file is in the list of files
        System.out.println("List of files name: " + actualListOfCSVFiles);
        assert actualListOfCSVFiles.containsKey(fileName);
        // check if the link is correct
        String actualLink = actualListOfCSVFiles.get(fileName);
        System.out.println("Link to the file: " + actualLink);
        assert actualLink.equals(expectedLink);
        // check if the content is correct)
        String actualCSVContent = onlineFileStorageManager.download(expectedLink);
        System.out.println("Content of the file: " + actualCSVContent);
        assert actualCSVContent.equals(expectedCSVContent);
    }
}
