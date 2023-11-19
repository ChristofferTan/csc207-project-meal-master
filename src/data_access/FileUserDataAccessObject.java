package data_access;

import entity.User;
import entity.UserFactory;
import use_case.add_friend.AddFriendUserDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

import java.io.*;
import java.util.*;

public class FileUserDataAccessObject implements SignupUserDataAccessInterface, LoginUserDataAccessInterface, AddFriendUserDataAccessInterface {
    private final File csvFile;
    private final Map<String, Integer> headers = new LinkedHashMap<>();
    private final Map<String, User> accounts = new HashMap<>();
    private final Map<String, ArrayList<String>> friends = new HashMap<>();
    private UserFactory userFactory;

    public FileUserDataAccessObject(String csvPath, UserFactory userFactory) throws IOException {
        this.userFactory = userFactory;

        csvFile = new File(csvPath);
        headers.put("username", 0);
        headers.put("password", 1);
        headers.put("name", 2);
        headers.put("age", 3);
        headers.put("gender", 4);
        headers.put("height", 5);
        headers.put("weight", 6);
        headers.put("activity level", 7);

        if (csvFile.length() == 0) {
            save();
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(csvFile))) {
                String header = reader.readLine();

                assert headers.equals("username,password,name,age,gender,height,weight,activity level");

                String row;
                while((row = reader.readLine()) != null) {
                    String[] col = row.split(",");
                    String username = String.valueOf(col[headers.get("username")]);
                    String password = String.valueOf(col[headers.get("password")]);
                    String name = String.valueOf(col[headers.get("name")]);
                    int age = Integer.parseInt(col[headers.get("age")]);
                    String gender = String.valueOf(col[headers.get("gender")]);
                    int height = Integer.parseInt(col[headers.get("height")]);
                    int weight = Integer.parseInt(col[headers.get("weight")]);
                    String activityLevel = String.valueOf(col[headers.get("activity level")]);

                    User user = userFactory.create(username, password, name, age, gender, height, weight, activityLevel);
                    accounts.put(username, user);
                }
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
            writer = new BufferedWriter(new FileWriter(csvFile));
            writer.write(String.join(",", headers.keySet()));
            writer.newLine();

            for (User user: accounts.values()) {
                String line = String.format("%s,%s,%s,%d,%s,%d,%d,%s", user.getUsername(), user.getPassword(),user.getName(), user.getAge(), user.getGender(), user.getHeight(), user.getWeight(), user.getActivityLevel());
                writer.write(line);
                writer.newLine();
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public User get(String username) {
        return accounts.get(username);
    }
}
