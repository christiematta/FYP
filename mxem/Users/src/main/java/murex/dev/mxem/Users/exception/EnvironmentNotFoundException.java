package murex.dev.mxem.Users.exception;

public class EnvironmentNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EnvironmentNotFoundException()
    {
        super("Error : Environment Not Found in environment repository");
    }
}
