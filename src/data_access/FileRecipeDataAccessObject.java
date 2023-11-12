package data_access;

import api.file.io.DownloadCSVFilesAPICaller;
import api.file.io.GetListofCSVFilesAPICaller;
import api.file.io.UploadCSVFilesAPICaller;
import entity.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FileRecipeDataAccessObject {
    private final LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
    private final HashMap<String, Recipe> recipes = new HashMap<>();  // label -> recipe
    private final RecipeFactory recipeFactory;

    public static void main(String[] args) throws IOException {
        FileRecipeDataAccessObject frdao = new FileRecipeDataAccessObject(new RecipeFactory());
    }

    public FileRecipeDataAccessObject(RecipeFactory recipeFactory) throws IOException {
        this.recipeFactory = recipeFactory;

        headers.put("label", 0);
        headers.put("recipeUrl", 1);
        headers.put("imagePath", 2);
        headers.put("calories", 3);
        headers.put("preparationTime", 4);
        headers.put("yield", 5);
        headers.put("ingredients", 6);

        // check if recipes.csv exist in the database
        HashMap<String, String> filesInDatabase = GetListofCSVFilesAPICaller.call();
        if (filesInDatabase.containsKey("recipes.csv")) {
            // if exist, get the latest version of recipes.csv
            System.out.println("Downloading recipes.csv from database... (removing recipes.csv from the database)");
            String recipesData = DownloadCSVFilesAPICaller.call(filesInDatabase.get("recipes.csv"));
            String[] rows = recipesData.split("\n");
            String header = rows[0];

            // For later: clean this up by creating a new Exception subclass and handling it in the UI.
            assert header.equals("label,recipeUrl,imagePath,calories,preparationTime,yield,ingredients");

            for (int i=1; i<rows.length; i++) {
                String row = rows[i];
                String[] col = row.split(",");
                String label = String.valueOf(col[headers.get("label")]);
                String recipeUrl = String.valueOf(col[headers.get("recipeUrl")]);
                String imagePath = String.valueOf(col[headers.get("imagePath")]);
                int calories = Integer.parseInt(col[headers.get("calories")]);
                int preparationTime = Integer.parseInt(col[headers.get("preparationTime")]);
                int yield = Integer.parseInt(col[headers.get("yield")]);
                ArrayList<String> ingredients = new ArrayList<>(Arrays.asList(Arrays.copyOfRange(col, headers.get("ingredients"), col.length)));

                Recipe recipe = this.recipeFactory.create(label, recipeUrl, imagePath, calories, ingredients, preparationTime, yield);
                recipes.put(label, recipe);
            }
            save();
        } else {
            // if not exist, create a new recipes.csv
            save();
        }
    }

    private void save() {
        BufferedWriter writer;
        try {
            File csvFile = new File("./recipes.csv");
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Recipe recipe : recipes.values()) {
                String formattedIngredients = String.join(",", recipe.getIngredients());
                String line = String.format("%s,%s,%s,%s,%s,%s,%s",
                        recipe.getLabel(), recipe.getRecipeUrl(), recipe.getImagePath(), recipe.getCalories(),
                        recipe.getPreparationTime(), recipe.getYield(), formattedIngredients);
                writer.write(line);
                writer.newLine();
            }

            writer.close();
            System.out.println("Uploading recipes.csv to database...");
            UploadCSVFilesAPICaller.call("./recipes.csv");
            System.out.println("Upload success! Download at " + GetListofCSVFilesAPICaller.call().get("recipes.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void save (Recipe recipe) {
        recipes.put(recipe.getLabel(), recipe);
        this.save();
    }

    public Recipe get(String label) {
        return recipes.get(label);
    }
}