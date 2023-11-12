package data_access;

import api.file.io.DownloadCSVFilesAPICaller;
import api.file.io.GetListofCSVFilesAPICaller;
import api.file.io.UploadCSVFilesAPICaller;
import entity.*;
import use_case.save_recipe.SaveRecipeDataAccessInterface;
import use_case.save_recipe.SaveRecipeOutputBoundary;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FilePlannerDataAccessObject implements SaveRecipeDataAccessInterface {
    private final LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
    private final HashMap<String, Planner> planners = new HashMap<>();  // username -> planner
    private final PlannerFactory plannerFactory;

    public static void main(String[] args) throws IOException {
        FilePlannerDataAccessObject fpdao = new FilePlannerDataAccessObject(new PlannerFactory(), new FileRecipeDataAccessObject(new RecipeFactory()));
    }

    public FilePlannerDataAccessObject(PlannerFactory plannerFactory, FileRecipeDataAccessObject fileRecipeDataAccessObject) throws IOException {
        this.plannerFactory = plannerFactory;

        headers.put("username", 0);
        headers.put("day", 1);
        headers.put("mealType", 2);
        headers.put("recipesLabel", 3);

        // check if planners.csv exist in the database
        HashMap<String, String> filesInDatabase = GetListofCSVFilesAPICaller.call();
        if (filesInDatabase.containsKey("planners.csv")) {
            // if exist, get the latest version of planners.csv
            System.out.println("Downloading planners.csv from database... (removing planners.csv from the database)");
            String plannersData = DownloadCSVFilesAPICaller.call(filesInDatabase.get("planners.csv"));
            String[] rows = plannersData.split("\n");
            String header = rows[0];

            // For later: clean this up by creating a new Exception subclass and handling it in the UI.
            assert header.equals("username,day,mealType,recipesLabel");

            for (int i=1; i<rows.length; i++) {
                String row = rows[i];
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String day = String.valueOf(col[headers.get("day")]);
                String mealType = String.valueOf(col[headers.get("mealType")]);
                String[] recipesLabel = Arrays.copyOfRange(col, headers.get("recipesLabel"), col.length);

                if (planners.containsKey(username)) {
                    HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes = planners.get(username).getWeeklyRecipes();
                    for (String recipeLabel : recipesLabel) {
                        weeklyRecipes.get(DayOfWeek.valueOf(day)).put(MealType.valueOf(mealType), fileRecipeDataAccessObject.get(recipeLabel));
                    }
                } else {
                    Planner planner = this.plannerFactory.create(username);
                    HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes = planner.getWeeklyRecipes();
                    for (String recipeLabel : recipesLabel) {
                        weeklyRecipes.get(DayOfWeek.valueOf(day)).put(MealType.valueOf(mealType), fileRecipeDataAccessObject.get(recipeLabel));
                    }
                    planners.put(username, planner);
                }
            }
            save();
        } else {
            // if not exist, create a new planners.csv
            save();
        }
    }

    private void save() {
        BufferedWriter writer;
        try {
            File csvFile = new File("./planners.csv");
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Planner planner : planners.values()) {
                HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes = planner.getWeeklyRecipes();
                for (DayOfWeek day : weeklyRecipes.keySet()) {
                    for (MealType mealType : weeklyRecipes.get(day).keySet()) {
                        String line = String.format("%s,%s,%s,%s",
                                planner.getUsername(), day, mealType, weeklyRecipes.get(day).get(mealType).getLabel());
                        writer.write(line);
                        writer.newLine();
                    }
                }
            }

            writer.close();
            System.out.println("Uploading planners.csv to database...");
            UploadCSVFilesAPICaller.call("./planners.csv");
            System.out.println("Upload success! Download at " + GetListofCSVFilesAPICaller.call().get("planners.csv"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void save(String username, DayOfWeek day, MealType mealType, Recipe recipe) {
        planners.get(username).getRecipesByDay(day).put(mealType, recipe);
        this.save();
    }

    @Override
    public Recipe get(String username, DayOfWeek day, MealType mealType) {
        return null;
    }
}
