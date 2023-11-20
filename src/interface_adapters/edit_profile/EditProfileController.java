package interface_adapters.edit_profile;

import entity.User;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;

import java.util.ArrayList;

public class EditProfileController {
    final EditProfileInputBoundary editProfileInteractor;

    public EditProfileController(EditProfileInputBoundary editProfileInteractor) {
        this.editProfileInteractor = editProfileInteractor;
    }

    public void execute(User user, String name, int age, String gender, double weight, double height) {
        EditProfileInputData editProfileInputData = new EditProfileInputData(user, name, age, gender, weight, height);

        editProfileInteractor.execute(editProfileInputData);
    }
}
