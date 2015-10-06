package DAO;

import models.Guest;
import models.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sander de Jong on 22-9-2015.
 */
public class OrderDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public OrderDAO(Connection connection) {
        this.connection = connection;
    }

    // insertOrder


    //updateOrder
    public void updateOrder(Order order) {
        try {
            this.preparedStatement = null;
            String sqlQuery = "UPDATE guest SET order_completed = ? WHERE order_timestamp = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setBoolean(1, order.isCompleted());
            //this.preparedStatement.setDate(2, order.getDate());
            this.preparedStatement.executeUpdate();
            this.connection.commit();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            try {
                connection.rollback();
                if (connection != null) {
                    System.err.print("Transaction is being rolled back ");
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        } finally {
            try {
                if (this.preparedStatement != null) {
                    preparedStatement.close();
                }
                if (!connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
