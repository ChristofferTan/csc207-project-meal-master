package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.edit_profile.EditProfileController;
import interface_adapters.edit_profile.EditProfilePresenter;
import interface_adapters.edit_profile.EditProfileViewModel;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import use_case.edit_profile.EditProfileOutputBoundary;
import view.EditProfileView;

public class EditProfileFactory {
    public EditProfileFactory() {}

    public static EditProfileView create(ViewManagerModel viewManagerModel,
                                         EditProfileViewModel editProfileViewModel,
                                         EditProfileDataAccessInterface editDataAccessObject) {
        EditProfileController editProfileController = createEditProfileUseCase(viewManagerModel, editProfileViewModel, editDataAccessObject);
        return new EditProfileView(editProfileController, editProfileViewModel);
    }

    private static EditProfileController createEditProfileUseCase(ViewManagerModel viewManagerModel, EditProfileViewModel editProfileViewModel, EditProfileDataAccessInterface editDataAccessObject) {
        EditProfileOutputBoundary editProfilePresenter = new EditProfilePresenter(editProfileViewModel, viewManagerModel);
        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(editDataAccessObject, editProfilePresenter);
        return new EditProfileController(editProfileInteractor);
    }
}
