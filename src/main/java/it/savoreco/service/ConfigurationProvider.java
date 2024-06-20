package it.savoreco.service;

import java.io.*;
import java.util.NoSuchElementException;
import java.util.Properties;

public class ConfigurationProvider {
    Properties properties = new Properties();

    public ConfigurationProvider() {
        File file = new File("C:\\Users\\proge\\Desktop\\bucket.cfg.xml");
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException ignore) {
            throw new NoSuchElementException("File not found");
        }

        readProperties(inputStream);
    }

    private void readProperties(InputStream fileReader) {
        try {
            properties.loadFromXML(fileReader);
        } catch (IOException e) {
            throw new RuntimeException("Cannot read from config file", e);
        }
    }

    public String getProperties(String key) {
        return properties.getProperty(key);
    }
}
