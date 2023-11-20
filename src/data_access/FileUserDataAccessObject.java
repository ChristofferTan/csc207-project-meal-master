package data_access;

import api.file.io.DownloadCSVFilesAPICaller;
import api.file.io.GetListofCSVFilesAPICaller;
import api.file.io.UploadCSVFilesAPICaller;
import entity.User;
import entity.UserFactory;
import use_case.add_friend.AddFriendUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.myprofile.MyProfileDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, AddFriendUserDataAccessInterface, MyProfileDataAccessInterface {
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private final Map<String, ArrayList<String>> friends = new HashMap<>();
    private UserFactory userFactory;

    public FileUserDataAccessObject(UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("name", 2);
        headers.put("age", 3);
        headers.put("gender", 4);
        headers.put("height", 5);
        headers.put("weight", 6);

        HashMap<String, String> filesInDatabase = GetListofCSVFilesAPICaller.call();
        if (filesInDatabase.containsKey("users.csv")) {
            String usersData = DownloadCSVFilesAPICaller.call(filesInDatabase.get("users.csv"));
            String[] rows = usersData.split("\n");
            String header = rows[0];

            assert headers.equals("username,password,name,age,gender,height,weight");

            for (int i=1;i<rows.length;i++) {
                String row = rows[i];
                String[] col = row.split(",");
                String username = String.valueOf(col[headers.get("username")]);
                String password = String.valueOf(col[headers.get("password")]);
                String name = String.valueOf(col[headers.get("name")]);
                int age = Integer.parseInt(col[headers.get("age")]);
                String gender = String.valueOf(col[headers.get("gender")]);
                int height = Integer.parseInt(col[headers.get("height")]);
                int weight = Integer.parseInt(col[headers.get("weight")]);

                User user = userFactory.create(username, password, name, age, gender, height, weight);
                accounts.put(username, user);
            }
        }
    }

    @Override
    public boolean existsByName(String identifier) {
        return accounts.containsKey(identifier);
    }

    @Override
    public boolean isFriend(String username, String friendUsername) {
        return friends.get(username).contains(friendUsername);
    }

    @Override
    public void addFriend(String username, String friendUsername) {
        if (!friends.containsKey(username)) {
            friends.put(username, new ArrayList<String>(Arrays.asList(friendUsername)));
        }
        friends.get(username).add(friendUsername);
        // save friend into the csv file
    }


    @Override
    public void save(User user) {
        accounts.put(user.getUsername(), user);
        this.save();
    }
    private void save() {
        BufferedWriter writer;
        try {
            File csvFile = new File("./users.csv");
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user: accounts.values()) {
                String line = String.format("%s,%s,%s,%d,%s,%d,%d", user.getUsername(), user.getPassword(),user.getName(), user.getAge(), user.getGender(), user.getHeight(), user.getWeight());
                writer.write(line);
                writer.newLine();
            }
            writer.close();
            UploadCSVFilesAPICaller.call("./users.csv");

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }
}
