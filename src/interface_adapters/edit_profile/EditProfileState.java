package interface_adapters.edit_profile;

public class EditProfileState {
    private String username;
    private String name;
    private int age;
    private String gender;
    private int height;
    private int weight;

    public EditProfileState(EditProfileState copy) {
        username = copy.username;
        name = copy.name;
        age = copy.age;
        gender = copy.gender;
        height = copy.height;
        weight = copy.weight;
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

    public int getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
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

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
