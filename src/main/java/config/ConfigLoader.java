package config;

import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private static final Properties properties = new Properties();

    static {
        try (InputStream input = ConfigLoader.class.getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                throw new RuntimeException("Файл конфигурации не найден!");
            }
            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при загрузке конфигурации: " + e.getMessage());
        }
    }

    public static String getBaseUrl() {
        return properties.getProperty("base.url");
    }

    public static String getHostname() {
        return properties.getProperty("hostname");
    }
}