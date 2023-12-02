package data_access;

import api.file.io.DownloadCSVFilesAPICaller;
import api.file.io.GetListofCSVFilesAPICaller;
import api.file.io.UploadCSVFilesAPICaller;
import entity.*;
import use_case.generate_recipe.GenerateRecipeDataAccessInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FileRecipeDataAccessObject implements GenerateRecipeDataAccessInterface {
    private final LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
    private final HashMap<String, Recipe> recipes = new HashMap<>();  // label -> recipe
    private final RecipeFactory recipeFactory;
    private final String FILE_NAME = "recipes.csv";
    private final String FILE_PATH = "./" + FILE_NAME;


    public FileRecipeDataAccessObject(RecipeFactory recipeFactory) {
        this.recipeFactory = recipeFactory;

        headers.put("label", 0);
        headers.put("recipeUrl", 1);
        headers.put("imagePath", 2);
        headers.put("calories", 3);
        headers.put("preparationTime", 4);
        headers.put("yield", 5);
        headers.put("ingredients", 6);

        fetch();
    }

    /**
     * Fetch (pull) the latest version of recipes.csv from the database into recipes
     */
    public void fetch() {
        // check if recipes.csv exist in the database
        HashMap<String, String> filesNameInDatabase = GetListofCSVFilesAPICaller.call();
        if (filesNameInDatabase.containsKey(FILE_NAME)) {
            // if exist, get the latest version of recipes.csv
            System.out.println("Downloading recipes.csv from database... (removing recipes.csv from the database)");
            String recipesData = DownloadCSVFilesAPICaller.call(filesNameInDatabase.get(FILE_NAME));
            String[] rows = recipesData.split("\n");
            String header = rows[0].trim();

            // For later: clean this up by creating a new Exception subclass and handling it in the UI.
            assert header.equals("label,recipeUrl,imagePath,calories,preparationTime,yield,ingredients");

            for (int i=1; i<rows.length; i++) {
                String row = rows[i].trim();
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

    public void save() {
        BufferedWriter writer;
        try {
            File csvFile = new File(FILE_PATH);
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
            UploadCSVFilesAPICaller.call(FILE_PATH);
            HashMap<String,String> listOfCSVFiles = GetListofCSVFilesAPICaller.call();
            System.out.println("Upload success! Download at " + listOfCSVFiles.get(FILE_NAME) + ". There's currently " + listOfCSVFiles.size() + " files in the database.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void save (Recipe recipe) {
        System.out.println("Downloading recipes.csv from database... (removing recipes.csv from the database)");
        DownloadCSVFilesAPICaller.call(GetListofCSVFilesAPICaller.call().get(FILE_NAME)); // remove recipes.csv from the database
        recipes.put(recipe.getLabel(), recipe);
        this.save();
    }

    public Recipe getRecipe(String label) {
        return recipes.get(label);
    }
}