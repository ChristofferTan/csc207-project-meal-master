package data_access;

import api.file.io.DownloadCSVFilesAPICaller;
import api.file.io.GetListofCSVFilesAPICaller;
import api.file.io.UploadCSVFilesAPICaller;
import entity.*;
import use_case.add_favorite_recipe.AddFavoriteRecipeUserDataAccessInterface;
import use_case.add_friend.AddFriendUserDataAccessInterface;
import use_case.delete_favorite_recipe.DeleteFavoriteRecipeDataAccessInterface;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.grocery_list.GroceryListDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.my_favorite_recipes.MyFavoriteRecipeDataAccessInterface;
import use_case.myprofile.MyProfileDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, AddFavoriteRecipeUserDataAccessInterface, EditProfileDataAccessInterface,
        MyProfileDataAccessInterface, MyFavoriteRecipeDataAccessInterface, DeleteFavoriteRecipeDataAccessInterface {
    private final PlannerFactory plannerFactory = new PlannerFactory();
    private final FilePlannerDataAccessObject plannerDataAccessObject = new FilePlannerDataAccessObject(plannerFactory, new FileRecipeDataAccessObject(new RecipeFactory()));

    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private final Map<String, ArrayList<String>> friends = new HashMap<>();
    private UserFactory userFactory;
    private final String FILE_NAME = "users.csv";
    private final String FILE_PATH = "./" + FILE_NAME;

    public FileUserDataAccessObject(UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("name", 2);
        headers.put("age", 3);
        headers.put("gender", 4);
        headers.put("height", 5);
        headers.put("weight", 6);
        headers.put("favoriteRecipes", 7);

        HashMap<String, String> filesNameInDatabase = GetListofCSVFilesAPICaller.call();
        if (filesNameInDatabase.containsKey(FILE_NAME)) {
            System.out.println("Downloading users.csv from database...");
            String usersData = DownloadCSVFilesAPICaller.call(filesNameInDatabase.get(FILE_NAME));
            String[] rows = usersData.split("\n");
            String header = rows[0].trim();

            assert header.equals("username,password,name,age,gender,height,weight,favoriteRecipes");

            for (int i=1;i<rows.length;i++) {
                String row = rows[i].trim();
                System.out.println(row);
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);
                String name = String.valueOf(col[headers.get("name")]);
                int age = Integer.parseInt(col[headers.get("age")]);
                String gender = String.valueOf(col[headers.get("gender")]);
                int height = Integer.parseInt(col[headers.get("height")]);
                int weight = Integer.parseInt(col[headers.get("weight")]);

                User user = userFactory.create(username, password, name, age, gender, height, weight);
                ArrayList<String> favoriteRecipes = user.getFavoriteRecipes();
                int idx = headers.get("favoriteRecipes");
                while(idx < col.length) {
                    String label = String.valueOf(col[idx]);
                    favoriteRecipes.add(label);
                    idx++;
                }
                accounts.put(username, user);
            }
            this.save();
        }
        else {
            // if not exist, create a new users.csv
            this.save();
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public void save(User user) {
        System.out.println("Downloading users.csv from database... (removing users.csv from the database)");
        DownloadCSVFilesAPICaller.call(GetListofCSVFilesAPICaller.call().get(FILE_NAME));
        accounts.put(user.getUsername(), user);
        this.save();
    }
    private void save() {
        BufferedWriter writer;
        try {
            File csvFile = new File(FILE_PATH);
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user: accounts.values()) {
                String favoriteRecipes = "";
                for (String label: user.getFavoriteRecipes()) {
                    if (favoriteRecipes == "") {
                        favoriteRecipes += label;
                    }
                    else {
                        favoriteRecipes += "," + label;
                    }
                }
                String line = String.format("%s,%s,%s,%d,%s,%d,%d,%s", user.getUsername(), user.getPassword(), user.getName(), user.getAge(), user.getGender(), user.getHeight(), user.getWeight(), favoriteRecipes);
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            System.out.println("Uploading users.csv to database...");
            UploadCSVFilesAPICaller.call(FILE_PATH);
            HashMap<String,String> listOfCSVFiles = GetListofCSVFilesAPICaller.call();
            System.out.println("Upload success! Download at " + listOfCSVFiles.get(FILE_NAME) + ". There's currently " + listOfCSVFiles.size() + " files in the database.");


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void editProfile(String username, String name, int age, String gender, int height, int weight) {
        System.out.println("Downloading users.csv from database... (removing users.csv from the database)");
        DownloadCSVFilesAPICaller.call(GetListofCSVFilesAPICaller.call().get(FILE_NAME));
        if (existsByName(username)) {
            User user = accounts.get(username);
            // DownloadCSVFilesAPICaller.call(GetListofCSVFilesAPICaller.call().get(FILE_NAME));
            user.setName(name);
            user.setAge(age);
            user.setGender(gender);
            user.setHeight(height);
            user.setWeight(weight);
        }
        this.save();
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }

    @Override
    public boolean isExists(String username, String label) {
        if (accounts.containsKey(username)) {
            User user  = accounts.get(username);
            return user.getFavoriteRecipes().contains(label);
        }
        return false;
    }

    @Override
    public void deleteFavoriteRecipe(String username, String label) {
        System.out.println("Downloading users.csv from database... (removing users.csv from the database)");
        DownloadCSVFilesAPICaller.call(GetListofCSVFilesAPICaller.call().get(FILE_NAME));
        if (existsByName(username)) {
            User user = accounts.get(username);
            if (user.getFavoriteRecipes().contains(label)) {
                user.getFavoriteRecipes().remove(label);
            }
        }
        this.save();
    }

    @Override
    public void saveFavoriteRecipe(String username, String label) {
        System.out.println("Downloading users.csv from database... (removing users.csv from the database)");
        DownloadCSVFilesAPICaller.call(GetListofCSVFilesAPICaller.call().get(FILE_NAME));
        if (existsByName(username)) {
            User user = accounts.get(username);
            if (!user.getFavoriteRecipes().contains(label)) {
                user.getFavoriteRecipes().add(label);
            }
        }
        this.save();
    }
}
