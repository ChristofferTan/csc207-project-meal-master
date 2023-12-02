package use_case.my_profile;

import entity.User;

public interface MyProfileDataAccessInterface {
    User get(String username);
}
