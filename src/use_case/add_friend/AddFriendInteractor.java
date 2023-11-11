package use_case.add_friend;

public class AddFriendInteractor implements AddFriendInputBoundary{
    final AddFriendUserDataAccessInterface addFriendDataAccesObject;
    final AddFriendOutputBoundary addFriendPresenter;

    public AddFriendInteractor(AddFriendUserDataAccessInterface addFriendDataAccesObject, AddFriendOutputBoundary addFriendPresenter) {
        this.addFriendDataAccesObject = addFriendDataAccesObject;
        this.addFriendPresenter = addFriendPresenter;
    }

    @Override
    public void execute(AddFriendInputData addFriendInputData) {
        if (!addFriendDataAccesObject.existsByName(addFriendInputData.getFriendUsername())) {
            addFriendPresenter.prepareFailView("user does not exist.");
        }
        else if (addFriendDataAccesObject.isFriend(addFriendInputData.getUsername(), addFriendInputData.getFriendUsername())) {
            addFriendPresenter.prepareFailView("you have added " + addFriendInputData.getFriendUsername() + " as friend.");
        }
        else {
            addFriendDataAccesObject.addFriend(addFriendInputData.getUsername(), addFriendInputData.getFriendUsername());
            addFriendPresenter.prepareSuccesView(new AddFriendOutputData(addFriendInputData.getFriendUsername(), false));
        }
    }
}
