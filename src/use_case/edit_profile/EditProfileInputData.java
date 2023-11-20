package use_case.edit_profile;

public class EditProfileInputData {
    private final String username;
    private final String name;
    private final int age;
    private final String gender;
    private final int weight;
    private final int height;

    public EditProfileInputData(String username, String name, int age, String gender, int weight, int height) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

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

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }
}
