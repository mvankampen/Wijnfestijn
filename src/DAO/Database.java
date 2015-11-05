package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Michael van Kampen
 * @version 0.1, november 2015
 *          Description:
 *          Database class is a singleton pattern that restricts the instantiation of a class to one object.
 */

public class Database {
    private static Database _instance;
    private Connection connection = null;
    private static final String DB_NAME = "wijnfestijnDB";
    private static final String USER = "postgres";
    private static final String PASSWORD = "";
    private static final String DRIVER = "org.postgresql.Driver";
    private static final String LOCATION = "jdbc:postgresql://localhost:5432/";

    /**
     * Private Constructor
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    private Database() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(LOCATION + DB_NAME, USER, PASSWORD);
        connection.setAutoCommit(false);
    }

    /**
     *
     * @return Database Instance
     * @throws SQLException an exception that provides information on a database access
     * error or other errors.
     */
    //allows retrieval of the current database, or making a new database instance
    public static Database getInstance() throws SQLException {
        if (_instance == null) {
            _instance = new Database();
        } else if (_instance.getConnection().isClosed()) {
            _instance = new Database();
        }

        return _instance;
    }

    /**
     *
     * @return A connection (session) with a specific
     * database. SQL statements are executed and results are returned
     * within the context of a connection.
     */
    public Connection getConnection() {
        return this.connection;
    }


}
