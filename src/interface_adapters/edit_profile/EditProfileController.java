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

    public void execute(String username, String name, int age, String gender, int weight, int height) {
        EditProfileInputData editProfileInputData = new EditProfileInputData(username, name, age, gender, weight, height);

        editProfileInteractor.execute(editProfileInputData);
    }
}
