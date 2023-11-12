package interface_adapters.add_friend;

import entity.User;
import use_case.add_friend.AddFriendInputBoundary;
import use_case.add_friend.AddFriendInputData;

public class AddFriendController {
    final AddFriendInputBoundary addFriendUseCaseInteractor;

    public AddFriendController(AddFriendInputBoundary addFriendUseCaseInteractor) {
        this.addFriendUseCaseInteractor = addFriendUseCaseInteractor;
    }
    public void execute(User user, String friendUsername) {
        AddFriendInputData addFriendInputData = new AddFriendInputData(user.getUsername(), friendUsername);
        addFriendUseCaseInteractor.execute(addFriendInputData);
    }

}
