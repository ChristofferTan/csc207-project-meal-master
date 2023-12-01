package use_case.edit_profile;

public class EditProfileInputData {
    private final String username;
    private final String name;
    private final int age;
    private final String gender;
    private final int height;
    private final int weight;

    public EditProfileInputData(String username, String name, int age, String gender, int height, int weight) {
        this.username = username;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
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
