package com.bryantchang.autodepsys.common;

import com.bryantchang.autodepsys.constant.Constants;

import java.io.*;
import java.util.Properties;

/**
 * Created by bryantchang on 2016/12/25.
 */
public class PropertyParser {
    public static Properties parseProperties() {
        String propertiesPath = Constants.ROOT_DIR + "/" + Constants.SYS_DIR + "/config.properties";
        File file = new File(propertiesPath);
        Properties properties = null;
        try {
            InputStream inputstream = new FileInputStream(file);
            properties = new Properties();
            try {
                properties.load(inputstream);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
