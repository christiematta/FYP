package murex.dev.mxem.Users.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

public class Tools {

    public static String removeAuthType(String token){
        List<String> items = Arrays.asList(token.split(" "));
        return(items.get(1));
    }


}
