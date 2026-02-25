package org.Abhinandan_Project.API.Utils;

import java.io.*;
import java.util.Properties;

public class ConfigManager {
    //to read the properties file from the config.properties

    private static Properties properties = new Properties();
    // create the object of properties class

    private static String path = "Config/config.properties";
    private static String env;
    static{
        env = System.getProperty("env", "qa");
        env = env.toLowerCase().trim();
        System.out.println("Running test in environment :"+env);
        switch (env)
        {
            case "dev":{
                path = "Config/config.dev.properties";
                break;
            }
            case "qa":{
                path = "Config/config.qa.properties";
                break;
            }
            case "uat":{
                path = "Config/config.uat.properties";
                break;
            }
            default:
                path = "Config/config.qa.properties";
        }

        //operation of loading the properties file in the memory
        //static block it will execute ! Once during class loading time.....
//        File configFile = new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"Config"+File.separator+"config.properties");
//        FileReader fileReader = null;
        InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(path);
        if(input==null){
            throw new RuntimeException("Cannot find hte file at the path: "+path);
        }


        try {
//            fileReader = new FileReader(configFile);
            properties.load(input);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
         catch (IOException e) {
            e.printStackTrace();
        }

    }
    public static String getProperty(String key ) {




        return properties.getProperty(key);

    }
}
