package interface_adapters.edit_profile;

import interface_adapters.ViewManagerModel;
import interface_adapters.my_profile.MyProfileViewModel;
import use_case.edit_profile.EditProfileOutputData;
import use_case.edit_profile.EditProfileOutputBoundary;

public class EditProfilePresenter implements EditProfileOutputBoundary{
    private final EditProfileViewModel editProfileViewModel;
    private final MyProfileViewModel myProfileViewModel;
    private final ViewManagerModel viewManagerModel;

    public EditProfilePresenter(EditProfileViewModel editProfileViewModel, MyProfileViewModel myProfileViewModel, ViewManagerModel viewManagerModel) {
        this.editProfileViewModel = editProfileViewModel;
        this.myProfileViewModel = myProfileViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    public void prepareSuccessView(EditProfileOutputData editProfileOutputData) {
        System.out.println("You have edited your profile successfully");
    }

    public void prepareFailView(String error) {
        System.out.println(error);
    }
}
