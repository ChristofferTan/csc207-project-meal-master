package app;

import interface_adapters.ViewManagerModel;
import interface_adapters.edit_profile.EditProfileController;
import interface_adapters.edit_profile.EditProfilePresenter;
import interface_adapters.edit_profile.EditProfileViewModel;
import interface_adapters.my_profile.MyProfileController;
import interface_adapters.my_profile.MyProfilePresenter;
import interface_adapters.my_profile.MyProfileViewModel;
import use_case.edit_profile.EditProfileDataAccessInterface;
import use_case.edit_profile.EditProfileInputBoundary;
import use_case.edit_profile.EditProfileInteractor;
import use_case.edit_profile.EditProfileOutputBoundary;
import use_case.my_profile.MyProfileDataAccessInterface;
import use_case.my_profile.MyProfileInputBoundary;
import use_case.my_profile.MyProfileInteractor;
import use_case.my_profile.MyProfileOutputBoundary;
import view.MyProfileView;

public class MyProfileFactory {
    private MyProfileFactory() {}
    public static MyProfileView create(ViewManagerModel viewManagerModel, MyProfileViewModel myProfileViewModel, EditProfileViewModel editProfileViewModel, MyProfileDataAccessInterface userDataAccessObject) {
        MyProfileController myProfileController = createMyProfileUseCase(viewManagerModel, myProfileViewModel, userDataAccessObject);
        EditProfileController editProfileController = createEditProfileController(viewManagerModel, editProfileViewModel, (EditProfileDataAccessInterface) userDataAccessObject, myProfileViewModel);
        return new MyProfileView(viewManagerModel, myProfileViewModel, editProfileViewModel, myProfileController, editProfileController);
    }

    public static MyProfileController createMyProfileUseCase(ViewManagerModel viewManagerModel, MyProfileViewModel myProfileViewModel, MyProfileDataAccessInterface dataAccessObject) {
        MyProfileOutputBoundary myProfileOutputBoundary = new MyProfilePresenter(myProfileViewModel, viewManagerModel);


        MyProfileInputBoundary myProfileInteractor = new MyProfileInteractor(dataAccessObject, myProfileOutputBoundary);
        return new MyProfileController(myProfileInteractor);
    }
    public static EditProfileController createEditProfileController(ViewManagerModel viewManagerModel, EditProfileViewModel editProfileViewModel, EditProfileDataAccessInterface dataAccessObject, MyProfileViewModel myProfileViewModel) {
        EditProfileOutputBoundary editProfileOutputBoundary = new EditProfilePresenter(editProfileViewModel, myProfileViewModel, viewManagerModel);

        EditProfileInputBoundary editProfileInteractor = new EditProfileInteractor(dataAccessObject, editProfileOutputBoundary);;
        return new EditProfileController(editProfileInteractor);
    }

}
