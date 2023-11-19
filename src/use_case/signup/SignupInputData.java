package use_case.signup;

public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;
    private String name;
    private int age;
    private String gender;
    private int height;
    private int weight;
    private String activityLevel;

    public SignupInputData(String username, String password, String repeatPassword, String name, int age, String gender, int height, int weight, String activityLevel) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
    }
    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
    public String getRepeatPassword(){
        return repeatPassword;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getActivityLevel() {
        return activityLevel;
    }

    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
    }
}
