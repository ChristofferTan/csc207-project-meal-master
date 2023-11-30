package use_case.edit_profile;

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
        String username = editProfileInputData.getUsername();
        String name = editProfileInputData.getName();
        int age = editProfileInputData.getAge();
        String gender = editProfileInputData.getGender();
        int weight = editProfileInputData.getWeight();
        int height = editProfileInputData.getHeight();

        userDataAccessObject.editProfile(username, name, age, gender, weight, height);

        EditProfileOutputData editProfileOutputData = new EditProfileOutputData(name, age, gender, height, weight);
        editProfilePresenter.prepareSuccessView(editProfileOutputData);
    }
}
