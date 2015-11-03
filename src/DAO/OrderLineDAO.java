package DAO;

import javafx.collections.ObservableList;
import models.Guest;
import models.Order;
import models.OrderLine;
import models.Wine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by michael on 06-10-15.
 */
public class OrderLineDAO {

    private Connection connection;
    private PreparedStatement preparedStatement;

    public OrderLineDAO(Connection connection) {
        this.connection = connection;
    }
    //adds all given orderlines to the database
    public void addOrderLines(ObservableList<OrderLine> orderLines, Order order) {
        try {
            this.preparedStatement = null;
            String sqlQuery = "INSERT INTO orderline (orderline_order_id, orderline_wine_id, orderline_amount) VALUES " + "(?, ?, ?)";
            this.preparedStatement = this.connection.prepareStatement(sqlQuery);

            for (int i = 0; i < orderLines.size(); i++) {
                preparedStatement.setInt(1, order.getId());
                preparedStatement.setInt(2, orderLines.get(i).getWine().getId());
                preparedStatement.setInt(3, orderLines.get(i).getAmount());
                preparedStatement.executeUpdate();
            }
            this.connection.commit();
        } catch (SQLException e) {
            System.out.print(e.getMessage());
            try {
                connection.rollback();
                if (connection != null) {
                    System.err.print("Transaction is being rolled back");
                }
            } catch (SQLException ex) {
                e.printStackTrace();
            }
        }
    }
    //updates a already existing orderline in the db with the given values
    public void updateOrderLine(OrderLine orderLine, Order order) {
        try {
            this.preparedStatement = null;
            String sqlQuery =
                "UPDATE  orderline SET orderline_order_id = ?, orderline_wine_id = ?, orderline_amount = ? WHERE orderline_order_id = ? AND orderline_wine_id = ?";
            this.preparedStatement = connection.prepareStatement(sqlQuery);
            this.preparedStatement.setInt(1, order.getId());
            this.preparedStatement.setInt(2, orderLine.getWine().getId());
            this.preparedStatement.setInt(3, orderLine.getAmount());
            this.preparedStatement.setInt(4, order.getId());
            this.preparedStatement.setInt(5, orderLine.getWine().getId());
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
        }
    }
}