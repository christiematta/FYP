package murex.dev.mxem.Environments.exception;

public class ProjectNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ProjectNotFoundException()
    {
        super("Error : Project not found");
    }
}