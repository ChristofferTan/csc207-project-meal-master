package interface_adapters.my_profile;

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

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameError() {
        return usernameError;
    }

    public void setUsernameError(String usernameError) {
        this.usernameError = usernameError;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameError() {
        return nameError;
    }

    public void setNameError(String nameError) {
        this.nameError = nameError;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getCalories() {
        return calories;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }
}
