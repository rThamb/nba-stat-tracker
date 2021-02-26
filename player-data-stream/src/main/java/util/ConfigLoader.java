package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {

    public static Properties load(String filePath) throws IOException {
        InputStream input = new FileInputStream(filePath);
        Properties prop = new Properties();
        prop.load(input);
        return prop;
    }
}
