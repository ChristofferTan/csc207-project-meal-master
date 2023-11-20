package app;

import interface_adapters.myprofile.MyProfileController;
import interface_adapters.myprofile.MyProfilePresenter;
import use_case.myprofile.MyProfileDataAccessInterface;
import use_case.myprofile.MyProfileInputBoundary;
import use_case.myprofile.MyProfileInteractor;
import use_case.myprofile.MyProfileOutputBoundary;

public class MyProfileFactory {
    private MyProfileFactory() {}

    public static MyProfileController createMyProfileUseCase(MyProfileDataAccessInterface dataAccessInterface) {
        MyProfileOutputBoundary myProfileOutputBoundary = new MyProfilePresenter();


        MyProfileInputBoundary myProfileInteractor = new MyProfileInteractor(dataAccessInterface, myProfileOutputBoundary);
        return new MyProfileController(myProfileInteractor);
    }
}
