package hibernatecrudservice;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.Data;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.Map;

@Data
public class Properties {
    public Properties(String filename) throws DatabaseException {
        try (InputStream stream = Main.class.getClassLoader().getResourceAsStream(filename)) {
            if (stream == null) {
                throw new DatabaseException("Cannot find resource " + filename);
            }

            String json = new String(stream.readAllBytes());
            Gson gson = new Gson();
            Type type = TypeToken.getParameterized(Map.class, String.class, String.class).getType();
            Map<String, String> propertiesMap = gson.fromJson(json, type);
            this.url = propertiesMap.get("url");
            this.username = propertiesMap.get("username");
            this.password = propertiesMap.get("password");
        } catch (IOException e) {
            throw new DatabaseException("I/O Error: " + e.getMessage(), e);
        }
    }

    private String url;
    private String username;
    private String password;
}
