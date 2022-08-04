package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    public static Properties getPropertyObject() throws IOException {
        //Read file and Create file path
        FileInputStream file = new FileInputStream("/Users/eliasarlington/Documents/Source/Hudl/src/main/java/config/config.proporties");

        //Create obj of properties
        Properties prop = new Properties();
        //Load file
        prop.load(file);

        return prop;
    }

    public static String geturl() throws IOException {
        return getPropertyObject().getProperty("url");
    }

    public static String getEmail() throws IOException {
        return getPropertyObject().getProperty("email");
    }

    public static String getPassword() throws IOException {
        return getPropertyObject().getProperty("password");
    }

    public static String getInvalidEmail() throws IOException {
        return getPropertyObject().getProperty("invalidEmail");
    }
    public static String getInvalidPassword() throws IOException {
        return getPropertyObject().getProperty("invalidPassword");
    }
}
