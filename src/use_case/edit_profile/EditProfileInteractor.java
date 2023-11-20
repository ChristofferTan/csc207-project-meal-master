package use_case.edit_profile;

import entity.User;

import java.util.ArrayList;

public class EditProfileInteractor implements EditProfileInputBoundary {
    final EditProfileDataAccessInterface userDataAccessObject;
    final EditProfileOutputBoundary editProfilePresenter;

    public EditProfileInteractor(EditProfileDataAccessInterface userDataAccessObject,
                                 EditProfileOutputBoundary editProfileOutputBoundary) {
        this.userDataAccessObject = userDataAccessObject;
        this.editProfilePresenter = editProfileOutputBoundary;
    }

    @Override
    public void execute(EditProfileInputData editProfileInputData) {
        User user = editProfileInputData.getUser();
        String name = editProfileInputData.getName();
        int age = editProfileInputData.getAge();
        String gender = editProfileInputData.getGender();
        double weight = editProfileInputData.getWeight();
        double height = editProfileInputData.getHeight();

        userDataAccessObject.editProfile(user, name, age, gender, weight, height);

        EditProfileOutputData editProfileOutputData = new EditProfileOutputData();
        editProfilePresenter.prepareSuccessView(editProfileOutputData);
    }
}
