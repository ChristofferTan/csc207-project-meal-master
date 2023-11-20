package use_case.myprofile;

import entity.User;

public interface MyProfileDataAccessInterface {
    User get(String username);
}
