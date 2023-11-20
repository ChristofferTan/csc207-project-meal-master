package use_case.add_friend;


public interface AddFriendOutputBoundary {
    void prepareSuccesView(AddFriendOutputData addFriendOutputData);
    void prepareFailView(String error);
}
