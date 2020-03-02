package murex.dev.mxem.Users.exception;

public class TokenNotValidException extends Exception {
    private static final long serialVersionUID = 1L;

    public TokenNotValidException()
    {
        super("Error : User not authorized");
    }
}
