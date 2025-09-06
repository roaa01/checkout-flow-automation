package config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigManager {
    private static Properties properties = new Properties();
    private static final String CONFIG_FILE = "config.properties";

    static {
        loadProperties();
    }

    private static void loadProperties() {
        try (InputStream inputStream = ConfigManager.class.getClassLoader()
                .getResourceAsStream(CONFIG_FILE)) {
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new RuntimeException("Configuration file '" + CONFIG_FILE + "' not found in classpath");
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration properties", e);
        }
    }

    public static String getProperty(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in configuration file");
        }
        return value;
    }

    public static String getProperty(String key, String defaultValue) {
        return properties.getProperty(key, defaultValue);
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    // Specific configuration getters
    public static String getBaseUrl() {
        return getProperty("base.url");
    }

    public static String getBrowser() {
        return getProperty("browser");
    }

    public static int getImplicitWait() {
        return getIntProperty("implicit.wait");
    }

    public static int getExplicitWait() {
        return getIntProperty("explicit.wait");
    }

    public static String getTestUserEmail() {
        return getProperty("test.user.email");
    }

    public static String getTestUserPassword() {
        return getProperty("test.user.password");
    }

    public static String getScreenshotPath() {
        return getProperty("screenshot.path");
    }

    public static String getReportsPath() {
        return getProperty("reports.path");
    }
}