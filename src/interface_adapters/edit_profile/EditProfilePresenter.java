package interface_adapters.edit_profile;

import interface_adapters.ViewManagerModel;
import use_case.edit_profile.EditProfileOutputData;
import use_case.edit_profile.EditProfileOutputBoundary;

public class EditProfilePresenter implements EditProfileOutputBoundary{
    private final EditProfileViewModel editProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditProfilePresenter(EditProfileViewModel editProfileViewModel, ViewManagerModel viewManagerModel) {
        this.editProfileViewModel = editProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    // TODO: implement with view
    public void prepareSuccessView(EditProfileOutputData editProfileOutputData) {
        System.out.println("You have edited your profile successfully");
    }

    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
