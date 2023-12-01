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
        int height = editProfileInputData.getHeight();
        int weight = editProfileInputData.getWeight();

        System.out.println("interactor : " + height + " " + weight);

        userDataAccessObject.editProfile(username, name, age, gender, height, weight);

        EditProfileOutputData editProfileOutputData = new EditProfileOutputData(name, age, gender, height, weight);
        editProfilePresenter.prepareSuccessView(editProfileOutputData);
    }
}
