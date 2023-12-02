package use_case.my_profile;

public class MyProfileInputData {
    final private String username;

    public MyProfileInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
