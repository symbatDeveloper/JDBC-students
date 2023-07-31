package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBC {

    public static Connection getConnaection(){
        String dbUrl = null;
        Properties properties = new Properties();
        FileInputStream file;

        try{
            file = new FileInputStream("src/main/resources/init.sql");
            properties.load(file);
            dbUrl= properties.getProperty("db.host");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        Connection connection = null;
        try {
            connection = DriverManager.getConnection(dbUrl);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
