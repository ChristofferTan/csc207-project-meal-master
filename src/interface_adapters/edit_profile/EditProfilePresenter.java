package interface_adapters.edit_profile;

import use_case.edit_profile.EditProfileOutputData;
import use_case.edit_profile.EditProfileOutputBoundary;

public class EditProfilePresenter implements EditProfileOutputBoundary{
    public EditProfilePresenter() {}

    public void prepareSuccessView(EditProfileOutputData editProfileOutputData) {
        System.out.println("You have edited your profile successfully");
    }
}
