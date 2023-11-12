package interface_adapters.add_friend;

import entity.User;

public class AddFriendState {
    private User user = null;
    private User userError = null;
    private String friendUsername = "";
    private String friendUsernameError = null;

    public AddFriendState(AddFriendState copy) {
        user = copy.user;
        userError = copy.userError;
        friendUsername = copy.friendUsername;
        friendUsernameError = copy.friendUsernameError;
    }
    public AddFriendState() {}
    public User getUser() {return user;}
    public User getUserError() {return userError;}
    public String getFriendUsername() {return friendUsername;}
    public String getFriendUsernameError() {return friendUsernameError;}

}
