package use_case.add_friend;

public class AddFriendInputData {
    final private String username;
    final private String friendUsername;

    public AddFriendInputData(String username, String friendUsername) {
        this.username = username;
        this.friendUsername = friendUsername;
    }

    public String getUsername() {
        return username;
    }

    public String getFriendUsername() {
        return friendUsername;
    }
}
