package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * Created by michael on 06-10-15.
 */
public class OrderLineDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public OrderLineDAO(Connection connection) {
        this.connection = connection;
    }
}
