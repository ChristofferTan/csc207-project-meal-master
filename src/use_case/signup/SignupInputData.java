package use_case.signup;

import java.util.ArrayList;

public class SignupInputData {
    final private String username;
    final private String password;
    final private String repeatPassword;

    public SignupInputData(String username, String password, String repeatPassword) {
        this.username = username;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }
    String getUsername() {
        return username;
    }
    String getPassword() {
        return password;
    }
<<<<<<< HEAD
    String getRepeatPassword(){
=======
    public String getRepeatPassword(){
>>>>>>> 400a6f05739da68f5805878f83f6b93d9f43f9d9
        return repeatPassword;
    }
}
