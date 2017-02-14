package common;

import java.io.*;
import java.util.Properties;

/**
 * Created by bryantchang on 2016/12/27.
 * 配置文件解析类
 */
public class PropertyParser {
    public static Properties parseProperties(String path) {
        //设置默认配置文件路径
        if(path.equals("") || path == null) {
            File file = new File("");
            path = file.getAbsolutePath() +  "/src/main/java/conf/agentconfig.properties";
        }
        File file = new File(path);
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
