package interface_adapters.edit_profile;

import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInputData;

import java.util.ArrayList;

public class EditProfileController {
    final EditProfileInputBoundary editProfileInteractor;

    public EditProfileController(EditProfileInputBoundary editProfileInteractor) {
        this.editProfileInteractor = editProfileInteractor;
    }

    public void execute(String name, int age, String gender, double weight, double height, String activityLevel,
                        ArrayList<String> dietLabels, ArrayList<String> healthLabels) {
        EditProfileInputData editProfileInputData = new EditProfileInputData(name, age, gender, weight, height,
                activityLevel, dietLabels, healthLabels);

        editProfileInteractor.execute(editProfileInputData);
    }
}
