package interface_adapters.edit_profile;

import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;

public class EditProfileController {
    final EditProfileInputBoundary editProfileInteractor;

    public EditProfileController(EditProfileInputBoundary editProfileInteractor) {
        this.editProfileInteractor = editProfileInteractor;
    }

    public void execute(String username, String name, int age, String gender, int height, int weight) {
        EditProfileInputData editProfileInputData = new EditProfileInputData(username, name, age, gender, height, weight);
        editProfileInteractor.execute(editProfileInputData);
    }
}
