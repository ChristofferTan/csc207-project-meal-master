package use_case.signup;

public class SignupOutputData {
    private final String username;
    private boolean useCaseFailed;

    public SignupOutputData(String username, boolean useCaseFailed) {
<<<<<<< HEAD

=======
>>>>>>> 400a6f05739da68f5805878f83f6b93d9f43f9d9
        this.username = username;
        this.useCaseFailed = useCaseFailed;
    }


    public String getUsername() {
        return username;
    }
<<<<<<< HEAD

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
=======
>>>>>>> 400a6f05739da68f5805878f83f6b93d9f43f9d9
}
