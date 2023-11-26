package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.edit_profile.EditProfileController;
import interface_adapters.edit_profile.EditProfilePresenter;
import interface_adapters.edit_profile.EditProfileViewModel;
import interface_adapters.myprofile.MyProfileController;
import interface_adapters.myprofile.MyProfilePresenter;
import interface_adapters.myprofile.MyProfileViewModel;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.myprofile.MyProfileDataAccessInterface;
import use_case.myprofile.MyProfileInputBoundary;
import use_case.myprofile.MyProfileInteractor;
import use_case.myprofile.MyProfileOutputBoundary;
import view.EditProfileView;

public class EditProfileFactory {
    public EditProfileFactory() {}

    public static EditProfileView create(ViewManagerModel viewManagerModel,
                                         EditProfileViewModel editProfileViewModel,
                                         MyProfileDataAccessInterface editDataAccessObject,
                                         MyProfileViewModel myProfileViewModel) {
        EditProfileController editProfileController = createEditProfileUseCase(viewManagerModel, editProfileViewModel, (EditProfileDataAccessInterface) editDataAccessObject, myProfileViewModel);
        MyProfileController myProfileController = createMyProfileUseCase(viewManagerModel, myProfileViewModel, editDataAccessObject);
        return new EditProfileView(editProfileController, editProfileViewModel, myProfileController, myProfileViewModel, viewManagerModel);
    }

    private static EditProfileController createEditProfileUseCase(ViewManagerModel viewManagerModel, EditProfileViewModel editProfileViewModel, EditProfileDataAccessInterface editDataAccessObject, MyProfileViewModel myProfileViewModel) {
        EditProfileOutputBoundary editProfilePresenter = new EditProfilePresenter(editProfileViewModel, myProfileViewModel, viewManagerModel);
        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(editDataAccessObject, editProfilePresenter);
        return new EditProfileController(editProfileInteractor);
    }

    public static MyProfileController createMyProfileUseCase(ViewManagerModel viewManagerModel, MyProfileViewModel myProfileViewModel, MyProfileDataAccessInterface dataAccessObject) {
        MyProfileOutputBoundary myProfileOutputBoundary = new MyProfilePresenter(myProfileViewModel, viewManagerModel);
        MyProfileInputBoundary myProfileInteractor = new MyProfileInteractor(dataAccessObject, myProfileOutputBoundary);
        return new MyProfileController(myProfileInteractor);
    }
}
