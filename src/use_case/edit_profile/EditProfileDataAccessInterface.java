package use_case.edit_profile;

import entity.User;
import use_case.myprofile.MyProfileDataAccessInterface;

import java.util.ArrayList;

public interface EditProfileDataAccessInterface extends MyProfileDataAccessInterface {
    void editProfile(String username, String name, int age, String gender, int height, int weight);
    void save(User user);
}
