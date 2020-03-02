package murex.dev.mxem.Users.exception;

public class TagNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public TagNotFoundException()
    {

        super("Error : Tag Not Found in Role repository");
    }
}