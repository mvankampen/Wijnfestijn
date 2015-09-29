package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class WineDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public WineDAO(Connection connection) {
        this.connection = connection;
    }

}
