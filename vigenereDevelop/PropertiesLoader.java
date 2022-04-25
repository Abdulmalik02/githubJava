package space.banka.ifmo.infosec.vigenere.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class PropertiesLoader {

    public static Properties loadFromResource(String resourceName) {
        try (InputStream inputStream = PropertiesLoader.class.getResourceAsStream(resourceName)) {
            try (InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8)) {
                Properties properties = new Properties();
                properties.load(inputStreamReader);
                return properties;
            }
        } catch (IOException e) {
            throw new RuntimeException(String.format("Failed to load properties from resource ‘%s’", resourceName), e);
        }
    }
}
