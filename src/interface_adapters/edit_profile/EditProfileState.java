package interface_adapters.edit_profile;

public class EditProfileState {
    private String username;
    private String name;
    private int age;
    private String gender;
    private double weight;
    private double height;

    public EditProfileState(EditProfileState copy) {
        username = copy.username;
        name = copy.name;
        age = copy.age;
        gender = copy.gender;
        weight = copy.weight;
        height = copy.height;
    }

    public EditProfileState() {}

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}
