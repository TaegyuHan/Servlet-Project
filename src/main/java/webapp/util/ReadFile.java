package webapp.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ReadFile {

    public static String read(HttpServletRequest request) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
        String line = reader.readLine();

        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();

        return sb.toString();
    }
}
