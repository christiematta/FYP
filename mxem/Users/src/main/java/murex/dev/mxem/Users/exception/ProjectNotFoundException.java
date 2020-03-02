package murex.dev.mxem.Users.exception;

public class ProjectNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public ProjectNotFoundException()
    {
        super("Error : Project Not Found in Project repository");
    }

}
