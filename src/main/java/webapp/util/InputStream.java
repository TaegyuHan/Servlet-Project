package webapp.util;

import org.json.JSONObject;

import javax.servlet.ServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputStream {

    public static JSONObject getJsonObject(ServletRequest servletRequest) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(servletRequest.getInputStream()));
        String line = reader.readLine();

        while (line != null) {
            sb.append(line);
            line = reader.readLine();
        }
        reader.close();

        return new JSONObject(sb.toString());
    }
}
