package use_case.edit_profile;

import entity.User;

import java.util.ArrayList;

public interface EditProfileDataAccessInterface {
    void editProfile(User user, String name, int age, String gender, double weight, double height);
}
