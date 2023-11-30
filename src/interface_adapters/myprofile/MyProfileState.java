package interface_adapters.myprofile;

import java.util.StringJoiner;

public class MyProfileState {
    private String username = "";
    private String usernameError = null;
    private String name = "";
    private String nameError = null;
    private int age;
    private String gender = "";
    private int height;
    private int weight;
    private int calories;

    public MyProfileState(MyProfileState copy) {
        username = copy.username;
        usernameError = copy.usernameError;
        name = copy.name;
        nameError = copy.nameError;
        age = copy.age;
        gender = copy.gender;
        height = copy.height;
        weight = copy.weight;
        calories = copy.calories;
    }
    public MyProfileState() {}

    public String getUsername() {
        return username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getNameError() {
        return nameError;
    }

    public int getAge() {
        return age;
    }

    public int getHeight() {
        return height;
    }

    public int getCalories() {
        return calories;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getWeight() {
        return weight;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }
}
