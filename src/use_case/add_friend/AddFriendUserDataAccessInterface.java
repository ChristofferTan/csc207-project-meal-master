package use_case.add_friend;

public interface AddFriendUserDataAccessInterface {
    boolean existsByName(String username);
    boolean isFriend(String username, String friendUsername);
    void addFriend(String username, String friendUsername);
}
