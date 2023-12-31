package data_access;

import api.file.io.FileIOFacade;
import entity.*;
import use_case.my_planner.MyPlannerDataAccessInterface;
import use_case.grocery_list.GroceryListDataAccessInterface;
import use_case.save_recipe.SaveRecipeDataAccessInterface;

import java.io.*;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class FilePlannerDataAccessObject implements SaveRecipeDataAccessInterface, MyPlannerDataAccessInterface, GroceryListDataAccessInterface {
    private final OnlineFileStorageManager onlineFileStorageManager = new FileIOFacade();
    private final LinkedHashMap<String, Integer> headers = new LinkedHashMap<>();
    private final HashMap<String, Planner> planners = new HashMap<>();  // username -> planner
    private final PlannerFactory plannerFactory;
    private final FileRecipeDataAccessObject fileRecipeDataAccessObject;
    private final String FILE_NAME = "planners.csv";
    private final String FILE_PATH = "./" + FILE_NAME;


    public FilePlannerDataAccessObject(PlannerFactory plannerFactory, FileRecipeDataAccessObject fileRecipeDataAccessObject) {
        this.plannerFactory = plannerFactory;
        this.fileRecipeDataAccessObject = fileRecipeDataAccessObject;
        headers.put("username", 0);
        headers.put("day", 1);
        headers.put("mealType", 2);
        headers.put("recipesLabel", 3);

        fetch();
    }

    /**
     * Fetch (pull) the latest version of planners.csv from the database into planners
     */
    public void fetch() {
        // check if planners.csv exist in the database
        HashMap<String, String> filesNameInDatabase = onlineFileStorageManager.getFileList();
        if (filesNameInDatabase.containsKey(FILE_NAME)) {
            // if exist, get the latest version of planners.csv
            System.out.println("Downloading planners.csv from database... (removing planners.csv from the database)");
            String plannersData = onlineFileStorageManager.download(filesNameInDatabase.get(FILE_NAME));
            String[] rows = plannersData.split("\n");
            String header = rows[0].trim();

            // For later: clean this up by creating a new Exception subclass and handling it in the UI.
            assert header.equals("username,day,mealType,recipesLabel");

            for (int i=1; i<rows.length; i++) {
                String row = rows[i].trim();
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String day = String.valueOf(col[headers.get("day")]);
                String mealType = String.valueOf(col[headers.get("mealType")]);
                String[] recipesLabel = Arrays.copyOfRange(col, headers.get("recipesLabel"), col.length);

                if (planners.containsKey(username)) {
                    System.out.println("planners already");
                    HashMap<MealType, Recipe> dailyRecipes = planners.get(username).getRecipesByDay(DayOfWeek.valueOf(day));
                    for (String recipeLabel : recipesLabel) {
                        Recipe recipe = fileRecipeDataAccessObject.getRecipe(recipeLabel);
                        assert recipe != null : "Recipe " + recipeLabel + " not found in database";
                        assert !dailyRecipes.containsKey(MealType.fromString(mealType)) : "Recipe " + mealType + " already exist in planners database, can't overwrite!";
                        System.out.println("mealType pas dalam if: " + MealType.fromString(mealType));
                        dailyRecipes.put(MealType.fromString(mealType), fileRecipeDataAccessObject.getRecipe(recipeLabel));
                    }
                } else {
                    Planner planner = this.plannerFactory.create(username);
                    HashMap<MealType,Recipe> dailyRecipes = planner.getRecipesByDay(DayOfWeek.valueOf(day));
                    for (String recipeLabel : recipesLabel) {
                        Recipe recipe = fileRecipeDataAccessObject.getRecipe(recipeLabel);
                        assert recipe != null;
                        assert !dailyRecipes.containsKey(MealType.fromString(mealType.toUpperCase()));
                        dailyRecipes.put(MealType.fromString(mealType), fileRecipeDataAccessObject.getRecipe(recipeLabel));
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

    /**
     * Save (push) the latest version of planners.csv from planners into the database
     */
    private void save() {
        BufferedWriter writer;
        try {
            File csvFile = new File(FILE_PATH);
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (Planner planner : planners.values()) {
                HashMap<DayOfWeek, HashMap<MealType, Recipe>> weeklyRecipes = planner.getWeeklyRecipes();
                for (DayOfWeek day : weeklyRecipes.keySet()) {
                    for (MealType mealType : weeklyRecipes.get(day).keySet()) {
                        Recipe currentRecipe = weeklyRecipes.get(day).get(mealType);
                        if (currentRecipe != null) {
                            String line = String.format("%s,%s,%s,%s",
                                    planner.getUsername(), day, mealType, weeklyRecipes.get(day).get(mealType).getLabel());
                            writer.write(line);
                            writer.newLine();
                        }
                    }
                }
            }
            writer.close();
            onlineFileStorageManager.upload(FILE_PATH);
            HashMap<String,String> listofCSVFiles = onlineFileStorageManager.getFileList();
            System.out.println("Uploaded planners.csv to database! Download at " + listofCSVFiles.get(FILE_NAME) + ". There's currently " + listofCSVFiles.size() + " files in the database.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save a new recipe to username's planner at day and mealType, write to planners.csv, and push to the database
     * @param username, day, mealType, recipe
     */
    @Override
    public void save(String username, DayOfWeek day, MealType mealType, Recipe recipe) {
        System.out.println("Downloading planners.csv from database... (removing planners.csv from the database)");
        onlineFileStorageManager.download(onlineFileStorageManager.getFileList().get(FILE_NAME));  // remove planners.csv from the database, since we'll save a new one
        if (!planners.containsKey(username)) {
            Planner planner = this.plannerFactory.create(username);
            planners.put(username, planner);
        }
        planners.get(username).getRecipesByDay(day).put(mealType, recipe);
        System.out.println(planners.get(username).getRecipesByDay(day).get(mealType));
        fileRecipeDataAccessObject.save(recipe);  // updates recipes.csv
        this.save();
    }

    public void saveNewPlanner(Planner planner) {
        onlineFileStorageManager.download(onlineFileStorageManager.getFileList().get(FILE_NAME));  // remove planners.csv from the database
        planners.put(planner.getUsername(), planner);
        this.save();
    }

    public Planner getPlanner(String username) {
        Planner userPlanner = planners.get(username);
        if (userPlanner == null) {
            PlannerFactory plannerFactory = new PlannerFactory();
            return plannerFactory.create(username);
        }
        return planners.get(username);
    }


    public boolean isPlannerExistsByUsername(String username) {
        return planners.containsKey(username);
    }
}
