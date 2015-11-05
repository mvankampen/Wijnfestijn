package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by michael on 28-09-15.
 */
public class Database {
    private static Database _instance;
    private Connection connection = null;
    private static final String DB_NAME = "wijnfestijnDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String LOCATION = "jdbc:postgresql://localhost:5433/";

    // Private constructor. Prevents instantiation from other classes.
    private Database() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(LOCATION + DB_NAME, USER, PASSWORD);
        connection.setAutoCommit(false);
    }
    //allows retrieval of the current database, or making a new database instance
    public static Database getInstance() throws SQLException {
        if (_instance == null) {
            _instance = new Database();
        } else if (_instance.getConnection().isClosed()) {
            _instance = new Database();
        }

        return _instance;
    }
    //allows retrieval of the current connection, used by DAOs
    public Connection getConnection() {
        return this.connection;
    }


}
