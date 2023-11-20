package use_case.myprofile;

import data_access.FileUserDataAccessObject;
import entity.User;

public class MyProfileInteractor implements MyProfileInputBoundary {
    final MyProfileDataAccessInterface dataAccessInterface;
    final MyProfileOutputBoundary myProfilePresenter;

    public MyProfileInteractor(MyProfileDataAccessInterface dataAccessInterface, MyProfileOutputBoundary myProfileOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.myProfilePresenter = myProfileOutputBoundary;
    }

    public void execute(MyProfileInputData myProfileInputData) {
        User user = dataAccessInterface.get(myProfileInputData.getUsername());
        MyProfileOutputData myProfileOutputData = new MyProfileOutputData(user);
        myProfilePresenter.prepareSuccessView(myProfileOutputData);
    }
}
