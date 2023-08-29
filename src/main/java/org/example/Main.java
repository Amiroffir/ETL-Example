package org.example;

import java.io.File;
import java.util.List;
import java.util.Map;

import static org.example.Extractor.extractFiles;

public class Main {
    public static void main(String[] args) {
        String jobDbPath = "C:\\Users\\Amir Offir\\Desktop\\jobDb";
        String specificJobPath = "\\1\\1.1"; // Set to null if not needed
        Map<String, List<File>> folderFilesMap = extractFiles(jobDbPath, specificJobPath);
        System.out.println(folderFilesMap);
    }
}