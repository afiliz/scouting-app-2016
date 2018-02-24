package com.scouting2016.tko;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Rahul Rameshbabu
 * @since 06 2015
 */

public class ConfigBean {
    public final int PORT;

    ConfigBean(Main.Mode mode) {
        Properties props = new Properties();
        try {

            if(System.getProperty("os.name").toLowerCase().contains("windows")){
                props.load(new FileInputStream("src/main/resources/" + (mode == Main.Mode.PRODUCTION ?
                        "server.properties" : "dev.properties")));
            } else {
                props.load(ConfigBean.class.getClassLoader().getResourceAsStream(mode == Main.Mode.PRODUCTION ?
                        "server.properties" : "dev.properties"));
            }

        } catch (IOException e) {
            System.err.println("IOException: Failed to load .properties setup file");
            e.printStackTrace();
        }
        PORT = Integer.parseInt(props.getProperty("port"));
    }
}