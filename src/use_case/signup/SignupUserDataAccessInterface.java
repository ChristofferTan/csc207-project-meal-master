package use_case.signup;

import entity.CommonUser;
import entity.User;

public interface SignupUserDataAccessInterface {
    boolean existsByName(String identifier);
    void save(User user);
    User get(String username);
}
