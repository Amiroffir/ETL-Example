package org.example;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Processing {

        List<Recipe> recipes = new ArrayList<>();
        public List<Recipe> jobListConverter(Map<String, List<File>> extractedFiles) {


            for (Map.Entry<String, List<File>> entry : extractedFiles.entrySet()) {
                 fileListConverter(entry.getValue());

            }

            return recipes;
        }

        private void fileListConverter(List<File> files) {
            Recipe recipe = new Recipe();
            List<ParsedFile> parsedFiles = new ArrayList<>();

            for (File file : files) {
              parsedFiles.add(classifyFile(file));
            }

            // Populate the Recipe object with parsed files, parameters, and areas
            // recipe.setFiles(parsedFiles);
            // recipe.setParameters(parameters);
            // recipe.setAreas(areas);

            // Construct the recipe object and add it to the list
            Recipe newRecipe = new Recipe();
            // newRecipe.setFiles(parsedFiles);
            // newRecipe.setParameters(parameters);
            // newRecipe.setAreas(areas);
             recipes.add(newRecipe);
        }


    private ParsedFile classifyFile(File file) {
            String extension = getFileExtension(file.getName());

            switch (extension.toLowerCase()) {
                case "json":
                    return new JsonFileParser().parseFile(file);
                case "txt":
                    return new TextFileParser().parseFile(file);
                // Add cases for other file types
                default:
                    // Handle unknown file type
                    return null;
            }
        }

        private String getFileExtension(String fileName) {
            int dotIndex = fileName.lastIndexOf(".");
            if (dotIndex > 0) {
                return fileName.substring(dotIndex + 1);
            }
            return "";
        }
    private String getFileExtension(File file) {
        String fileName = file.getName();
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
    }