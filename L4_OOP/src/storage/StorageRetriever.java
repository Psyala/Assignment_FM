package storage;

import javax.swing.filechooser.FileSystemView;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class StorageRetriever {

    private static final String PROPERTIES_FILE_NAME = "config.properties";
    private static final String PROPERTIES_FILE_PATH = FileSystemView.getFileSystemView().getDefaultDirectory().getPath() + "\\main.FootballManager\\";
    private static final String PROPERTIES_FULL_PATH = PROPERTIES_FILE_PATH + PROPERTIES_FILE_NAME;

    //Makes sure that the file path for the properties files exists and that the file itself exists - if they dont it creates them
    private static void ensureFilesExist() {
        try {
            if (!Files.exists(Paths.get(PROPERTIES_FILE_PATH))) {
                Files.createDirectories(Paths.get(PROPERTIES_FILE_PATH));
            }
            if (!Files.exists(Paths.get(PROPERTIES_FULL_PATH))) {
                Files.createFile(Paths.get(PROPERTIES_FULL_PATH));
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }

    //Loads all properties in file to memory object
    private static Properties getProperties() {
        ensureFilesExist();

        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(PROPERTIES_FULL_PATH));
            return properties;
        } catch (Exception ex) {
            System.out.println(ex);
            return null;
        }
    }

    //Stores all properties in memory object to file
    private static boolean storeProperties(Properties properties) {
        try {
            properties.store(new FileWriter(PROPERTIES_FULL_PATH), "Storing Properties");
            return true;
        } catch (Exception ex) {
            System.out.println(ex);
            return false;
        }
    }

    //Deletes a property from memory object
    protected static boolean deleteProperty(String property) {
        Properties properties = getProperties();
        if (properties.containsKey(property)) {
            properties.remove(property);
            return storeProperties(properties);
        } else {
            return false;
        }
    }

    //Gets a property value from memory object
    static String getPropertyValue(String property, String defaultValue) {
        Properties properties = getProperties();
        if (properties.containsKey(property)) {
            return properties.getProperty(property, defaultValue);
        }
        return defaultValue;
    }

    //Sets a property value in the memory object
    static void setPropertyValue(String property, String value) {
        Properties properties = getProperties();
        properties.setProperty(property, value);
        storeProperties(properties);
    }
}
