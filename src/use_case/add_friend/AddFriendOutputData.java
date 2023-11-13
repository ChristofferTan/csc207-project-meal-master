package use_case.add_friend;

public class AddFriendOutputData {
    private final String friendUsername;
    private boolean useCaseFailed;

    public AddFriendOutputData(String friendUsername, boolean useCaseFailed) {
        this.friendUsername = friendUsername;
        this.useCaseFailed = useCaseFailed;
    }


    public String getFriendUsername() {
        return friendUsername;
    }
}
