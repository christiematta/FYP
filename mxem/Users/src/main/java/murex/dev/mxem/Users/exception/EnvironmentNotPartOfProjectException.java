package murex.dev.mxem.Users.exception;

public class EnvironmentNotPartOfProjectException extends Exception {
    private static final long serialVersionUID = 1L;

    public EnvironmentNotPartOfProjectException()
    {
        super("Error : Environment not part of the specified project");
    }
}
