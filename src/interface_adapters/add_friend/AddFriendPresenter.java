package interface_adapters.add_friend;

import interface_adapters.ViewManagerModel;
import use_case.add_friend.AddFriendOutputBoundary;
import use_case.add_friend.AddFriendOutputData;

public class AddFriendPresenter implements AddFriendOutputBoundary {
    private final AddFriendViewModel addFriendViewModel;
    private final ViewManagerModel viewManagerModel;

    public AddFriendPresenter(AddFriendViewModel addFriendViewModel, ViewManagerModel viewManagerModel) {
        this.addFriendViewModel = addFriendViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccesView(AddFriendOutputData addFriendOutputData) {
        System.out.println(addFriendOutputData.getFriendUsername() + " is now your friend.");
    }

    @Override
    public void prepareFailView(String error) {
        // AddFriendState addFriendState = addFriendViewModel.getState();
        System.out.println(error);
    }
}
