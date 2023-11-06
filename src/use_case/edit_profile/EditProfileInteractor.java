package use_case.edit_profile;

public class EditProfileInteractor implements EditProfileInputBoundary {
    final EditProfileDataAccessInterface dataAccessInterface;
    final EditProfileOutputBoundary editProfilePresenter;

    public EditProfileInteractor(EditProfileDataAccessInterface dataAccessInterface,
                                 EditProfileOutputBoundary editProfileOutputBoundary) {
        this.dataAccessInterface = dataAccessInterface;
        this.editProfilePresenter = editProfileOutputBoundary;
    }

    @Override
    public void execute(EditProfileInputData editProfileInputData) {
        EditProfileOutputData editProfileOutputData = new EditProfileOutputData();
        editProfilePresenter.prepareSuccessView(editProfileOutputData);
    }
}
