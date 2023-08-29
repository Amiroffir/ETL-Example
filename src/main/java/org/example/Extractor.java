package org.example;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Extractor {

    public static Map<String, List<File>> extractFiles(String jobDbPath, String specificJobPath) {
        Map<String, List<File>> folderFilesMap = new HashMap<>();

        if (specificJobPath != null) {
            File specificJobFolder = new File(jobDbPath, specificJobPath);
            extractFromSubfolders(specificJobFolder, folderFilesMap);
        } else {
            File jobDbFolder = new File(jobDbPath);
            extractFromSubfolders(jobDbFolder, folderFilesMap);
        }

        return folderFilesMap;
    }

    private static void extractFromSubfolders(File folder, Map<String, List<File>> folderFilesMap) {
        File[] subfolders = folder.listFiles(File::isDirectory);

        for (File subfolder : subfolders) {
            if (subfolder.getName().equals("fusion600")) {
                List<File> fileList = listFilesInFolder(subfolder);
                folderFilesMap.put(folder.getAbsolutePath(), fileList);
            } else {
                extractFromSubfolders(subfolder, folderFilesMap);
            }
        }
    }

    private static List<File> listFilesInFolder(File folder) {
        List<File> fileList = new ArrayList<>();
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                Path path = file.toPath();
                if (Files.isSymbolicLink(path)) {
                    try {
                        Path realPath = file.toPath().toRealPath();
                        File realFile = realPath.toFile();
                        fileList.add(realFile);
                    } catch (IOException e) {
                        // Handle exception
                    }
                } else {
                    fileList.add(file);
                }
            }
        }

        return fileList;
    }


   /* public static Map<String, List<File>> extractFiles(String jobDbPath, String specificJobPath) {
        Map<String, List<File>> folderFilesMap = new HashMap<>();

        if (specificJobPath != null) {
            File specificJobFolder = new File(jobDbPath, specificJobPath);
            extractFromSubfolders(specificJobFolder, folderFilesMap);
        } else {
            File jobDbFolder = new File(jobDbPath);
            extractFromSubfolders(jobDbFolder, folderFilesMap);
        }

        return folderFilesMap;
    }

    private static void extractFromSubfolders(File folder, Map<String, List<File>> folderFilesMap) {
        File[] subfolders = folder.listFiles(File::isDirectory);

        for (File subfolder : subfolders) {
            if (containsFusion600(subfolder)) {
                List<File> fileList = listFilesInFolder(subfolder);
                folderFilesMap.put(subfolder.getAbsolutePath(), fileList);
            }
            extractFromSubfolders(subfolder, folderFilesMap); // Recurse into subfolders
        }
    }

    private static boolean containsFusion600(File folder) {
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isDirectory() && file.getName().equals("fusion600")) {
                return true;
            }
        }

        return false;
    }

    private static List<File> listFilesInFolder(File folder) {
        List<File> fileList = new ArrayList<>();
        extractFilesFromFolderAndSubfolders(folder, fileList);
        return fileList;
    }

    private static void extractFilesFromFolderAndSubfolders(File folder, List<File> fileList) {
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isFile()) {
                fileList.add(file);
            } else if (file.isDirectory()) {
                extractFilesFromFolderAndSubfolders(file, fileList);
            }
        }
    }*/
}