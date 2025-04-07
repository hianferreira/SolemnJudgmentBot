package dev.suki;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DataBaseConnection {
    static Dotenv dotenv = Dotenv.load();

    private static final String URL_DATABASE = dotenv.get("URL_DATABASE");
    private static final String USER_DATABASE = dotenv.get("USER_DATABASE");
    private static final String PASSWORD_DATABASE = dotenv.get("PASSWORD_DATABASE");

    public static Connection getConnection(){
        try {
            return DriverManager.getConnection(URL_DATABASE, USER_DATABASE, PASSWORD_DATABASE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
